package com.xiaoqianghe.wqbaselib.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Author：Wq
 * Date：2018/1/4 14:49
 * Description：//todo
 */

public class ImageUtils {

    public static byte[] Bitmap2Bytes(Bitmap bm, int MaxSize) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] result = null;

        int compressQuality = 100;
        while (true) {
            if (compressQuality <= 0) {
                break;
            }

            bm.compress(Bitmap.CompressFormat.JPEG, compressQuality, output);

            result = output.toByteArray();

            if (compressQuality < 40) {//30
                break;
            }

            if (result.length > MaxSize) {
                compressQuality = compressQuality - 10;
                output.reset();
                continue;
            } else {
                try {
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        return result;
    }


    //========
    /**
     * 获取和保存当前屏幕的截图
     */
    public static String  GetandSaveCurrentImage(Activity activity)
    {

        String filepath="";
        //1.构建Bitmap
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();

        Bitmap Bmp = Bitmap.createBitmap( w, h, Bitmap.Config.ARGB_8888 );

        //2.获取屏幕
        View decorview = activity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        Bmp = decorview.getDrawingCache();

        String SavePath = getSDCardPath()+"/AndyDemo/ScreenImage";

        //3.保存Bitmap
        try {
            File path = new File(SavePath);
            //文件
            filepath = SavePath + "/"+getTime()+"screen.png";
            File file = new File(filepath);
            if(!path.exists()){
                path.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            if (null != fos) {
                Bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();

                Toast.makeText(ContextUtils.getApplication(), "截屏文件已保存至SDCard/AndyDemo/ScreenImage/下", Toast.LENGTH_LONG).show();


            }

        } catch (Exception e) {
            e.printStackTrace();
            return filepath;

        }
        return filepath;
    }

    /**
     * 获取SDCard的目录路径功能
     * @return
     */
    private static String getSDCardPath(){
        File sdcardDir = null;
        //判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if(sdcardExist){
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }

    public static String getTime(){
        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳
        String  str=String.valueOf(time);
        return str;
    }


    public static boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

}
