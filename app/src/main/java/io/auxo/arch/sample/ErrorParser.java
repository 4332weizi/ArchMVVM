package io.auxo.arch.sample;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.auxo.arch.sample.exception.AppException;
import retrofit2.HttpException;

/**
 * @author Victor Chiu
 */
public class ErrorParser {

    public static boolean parse(Throwable throwable, StringBuilder errorMsg) {
        if (throwable instanceof JsonSyntaxException) {
            errorMsg.append("服务器返回数据格式有误");
            return true;
        } else if (throwable instanceof SocketTimeoutException) {
            errorMsg.append("网络连接超时");
            return true;
        } else if (throwable instanceof IOException) {
            errorMsg.append("您的网络不给力，请检测网络后重试");
            return true;
        } else if (throwable instanceof UnknownHostException) {
            errorMsg.append("服务器异常");
            return true;
        } else if (throwable instanceof HttpException) {
            String message = null;
            switch (((HttpException) throwable).code()) {
                case 401:
                    message = "登录超时";
                    break;
                default:
                    message = "服务暂不可用";
                    break;
            }
            errorMsg.append(message);
            return true;
        } else if (throwable instanceof AppException) {
            errorMsg.append(throwable.getMessage());
            return true;
        }
        errorMsg.append("未知错误");
        throwable.printStackTrace();
        return false;
    }

    public static String parse(Throwable throwable) {
        StringBuilder errorMsg = new StringBuilder();
        parse(throwable, errorMsg);
        return errorMsg.toString();
    }
}
