package com.github.api;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class HttpsSocketFactoryBuilder {

    private TrustManagerFactory mTrustManagerFactory;
    private KeyManagerFactory mKeyManagerFactory;

    public HttpsSocketFactoryBuilder trust(String trust) {
        KeyStore keyStore = readKeystore(trust);
        try {
            if (keyStore != null) {
                mTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                mTrustManagerFactory.init(keyStore);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return this;
    }

    public HttpsSocketFactoryBuilder client(String client, String password) {
        KeyStore keyStore = readKeystore(client);
        try {
            if (keyStore != null) {
                mKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                mKeyManagerFactory.init(keyStore, password.toCharArray());
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SSLSocketFactory build() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            KeyManager keyManagers[] = null;
            if (mKeyManagerFactory != null) {
                keyManagers = mKeyManagerFactory.getKeyManagers();
            }
            TrustManager trustManagers[] = null;
            if (mTrustManagerFactory != null) {
                trustManagers = mTrustManagerFactory.getTrustManagers();
            }
            sslContext.init(keyManagers, trustManagers, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    private KeyStore readKeystore(String key) {
        KeyStore keyStore = null;
        try {

            byte[] der = loadPemCertificate(new ByteArrayInputStream(key.getBytes()));

            ByteArrayInputStream derInputStream = new ByteArrayInputStream(der);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certificateFactory.generateCertificate(derInputStream);

            String alias = cert.getSubjectX500Principal().getName();

            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null);
            trustStore.setCertificateEntry(alias, cert);

            keyStore = trustStore;

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyStore;
    }

    protected byte[] loadPemCertificate(InputStream certificateStream) throws IOException {

        byte[] der = null;
        BufferedReader br = null;

        try {
            StringBuilder buf = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(certificateStream));

            String line = br.readLine();
            while (line != null) {
                if (!line.startsWith("--")) {
                    buf.append(line);
                }
                line = br.readLine();
            }
            String pem = buf.toString();
            der = Base64.decode(pem, Base64.DEFAULT);
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return der;
    }

}