package io.auxo.bindingadapters;

import androidx.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.net.URL;

/**
 * @author Victor
 */
public final class ImageViewBindingAdapter {

    private ImageViewBindingAdapter() {
    }

    @BindingAdapter(value = {"bitmap", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageBitmap(ImageView view, Bitmap bitmap,
                                      Object placeholder, Object error,
                                      Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(bitmap), placeholder, error, fallback, options);
    }

    @BindingAdapter(value = {"drawable", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageDrawable(ImageView view, Drawable drawable,
                                        Object placeholder, Object error,
                                        Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(drawable), placeholder, error, fallback, options);
    }

    @BindingAdapter(value = {"url", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageString(ImageView view, String string,
                                      Object placeholder, Object error,
                                      Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(string), placeholder, error, fallback, options);
    }

    @BindingAdapter(value = {"uri", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageUri(ImageView view, Uri uri,
                                   Object placeholder, Object error,
                                   Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(uri), placeholder, error, fallback, options);
    }

    @BindingAdapter(value = {"file", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageFile(ImageView view, File file,
                                    Object placeholder, Object error,
                                    Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(file), placeholder, error, fallback, options);
    }

    @BindingAdapter(value = {"res", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageRes(ImageView view, @DrawableRes Integer res,
                                   Object placeholder, Object error,
                                   Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(res), placeholder, error, fallback, options);
    }

    @Deprecated
    @BindingAdapter(value = {"URL", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageUrl(ImageView view, URL url,
                                   Object placeholder, Object error,
                                   Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(url), placeholder, error, fallback, options);
    }

    @BindingAdapter(value = {"bytes", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageBytes(ImageView view, byte[] bytes,
                                     Object placeholder, Object error,
                                     Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(bytes), placeholder, error, fallback, options);
    }

    @BindingAdapter(value = {"android:src", "placeholder", "error", "fallback", "options"}, requireAll = false)
    public static void setImageSrc(ImageView view, Object obj,
                                   Object placeholder, Object error,
                                   Object fallback, RequestOptions options) {
        setImageRequest(view, Glide.with(view).load(obj), placeholder, error, fallback, options);
    }

    private static void setImageRequest(ImageView view, RequestBuilder<Drawable> request,
                                        Object placeholder, Object error,
                                        Object fallback, RequestOptions options) {
        request.apply(GlideConfig.getDefaultRequestOptions())
                .apply(getPlaceholdersOptions(placeholder, error, fallback));
        if (options != null) {
            request.apply(options);
        }
        request.into(view);
    }

    private static RequestOptions getPlaceholdersOptions(
            Object placeholder, Object error, Object fallback) {
        RequestOptions options = new RequestOptions();
        applyPlaceholder(options, placeholder);
        applyError(options, error);
        applyFallback(options, fallback);
        return options;
    }

    private static void applyPlaceholder(RequestOptions options, Object placeholder) {
        if (placeholder == null) {
            return;
        }
        if (placeholder instanceof Drawable) {
            options.placeholder((Drawable) placeholder);
        }
        if (placeholder instanceof Integer) {
            options.placeholder((Integer) placeholder);
        }
    }

    private static void applyError(RequestOptions options, Object error) {
        if (error == null) {
            return;
        }
        if (error != null && error instanceof Drawable) {
            options.error((Drawable) error);
        }
        if (error != null && error instanceof Integer) {
            options.error((Integer) error);
        }
    }

    private static void applyFallback(RequestOptions options, Object fallback) {
        if (fallback == null) {
            return;
        }
        if (fallback != null && fallback instanceof Drawable) {
            options.fallback((Drawable) fallback);
        }
        if (fallback != null && fallback instanceof Integer) {
            options.fallback((Integer) fallback);
        }
    }
}
