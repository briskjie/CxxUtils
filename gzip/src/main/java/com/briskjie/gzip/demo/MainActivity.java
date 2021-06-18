package com.briskjie.gzip.demo;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.briskjie.gzip.R;
import com.briskjie.gzip.utils.GzipUtil;
import com.briskjie.gzip.utils.gzip.TarArchiveInputStream;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final File targetFile = new File(getFilesDir(), "cxx.html");
        if (!targetFile.exists()) {
            new Thread() {
                public void run() {
                    AssetManager assets = getAssets();
//                    //解压
                    try {
                        File sdcard = getFilesDir();
                        InputStream is = assets.open("qk/output.tar");

                        TarArchiveInputStream tais = new TarArchiveInputStream(is);
                        GzipUtil.deTarFile(sdcard.getPath(), tais);
                        tais.close();
                        System.out.println("解压成功");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }.start();
        }
    }

}
