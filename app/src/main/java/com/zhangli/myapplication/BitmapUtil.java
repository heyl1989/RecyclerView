package com.zhangli.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.InputStream;

/**
 * 图片处理器
 */
public class BitmapUtil {
    private Bitmap bitmap;

    public BitmapUtil(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public BitmapUtil() {
    }

    /**
     * 缩放处理
     *
     * @param scaling 缩放比例
     * @return 缩放后的图片
     */
    public Bitmap scale(float scaling) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaling, scaling);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 将给定资源ID的Drawable转换成Bitmap
     *
     * @param context 上下文
     * @param resId   Drawable资源文件的ID
     * @return 新的Bitmap
     */
    public Bitmap drawableToBitmap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

}
