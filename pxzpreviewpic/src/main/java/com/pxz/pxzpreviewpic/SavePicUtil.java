package com.pxz.pxzpreviewpic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * 类说明：保存图片
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/9/5 10:17
 */
public class SavePicUtil {
    /**
     * 保存图片到相册
     * @param context 上下文
     * @param bmp 图片的bitmap
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "相册");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsoluteFile())));
    }

    /**
     * 获取图片缓存地址
     */
    public static String getImagePath(String imgUrl, Activity activity) {
        String path = null;
        FutureTarget<File> future = Glide.with(activity).load(imgUrl).downloadOnly(500, 500);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }
}