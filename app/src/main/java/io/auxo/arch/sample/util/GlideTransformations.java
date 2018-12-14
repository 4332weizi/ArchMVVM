package io.auxo.arch.sample.util;

import com.bumptech.glide.load.Transformation;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;

public class GlideTransformations {

    public static Transformation circle() {
        return new CropCircleTransformation();
    }

    public static Transformation circle(int width, int height) {
        return new CropTransformation(width, height);
    }
}
