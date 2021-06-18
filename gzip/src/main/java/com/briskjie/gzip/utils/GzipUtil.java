package com.briskjie.gzip.utils;

import com.briskjie.gzip.utils.gzip.TarArchiveEntry;
import com.briskjie.gzip.utils.gzip.TarArchiveInputStream;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {
    /**
     * gzip压缩
     *
     * @param srcFile    源文件
     * @param targetFile 目标文件
     */
    public static void zip(File srcFile, File targetFile) {
        FileInputStream fis = null;
        GZIPOutputStream gos = null;
        try {
            fis = new FileInputStream(srcFile);
            // 往外写的时候, 用GZIPOutputStream, 直接写成压缩文件, 包装流
            gos = new GZIPOutputStream(new FileOutputStream(targetFile));
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                gos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuitely(fis);
            closeQuitely(gos);
        }
    }

    public static void zip(OutputStream out, File targetFile) {
        FileInputStream fis = null;
        GZIPOutputStream gos = null;
        try {
            // 往外写的时候, 用GZIPOutputStream, 直接写成压缩文件, 包装流
            gos = new GZIPOutputStream(out);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                gos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuitely(fis);
            closeQuitely(gos);
        }
    }

    /**
     * 解压缩
     *
     * @param srcFile    源文件 压缩包
     * @param targetFile 目标文件 普通文件
     */
    public static void unzip(File srcFile, File targetFile) {
        GZIPInputStream gis = null;
        FileOutputStream fos = null;
        try {
            gis = new GZIPInputStream(new FileInputStream(srcFile));
            fos = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuitely(gis);
            closeQuitely(fos);
        }
    }

    /**
     * 关闭流
     *
     * @param stream
     */
    public static void closeQuitely(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 解压缩
     * @param is 输入流 压缩包
     * @param targetFile 目标文件 普通文件
     */
    public static void unzip(InputStream is, File targetFile) {
        GZIPInputStream gis = null;
        FileOutputStream fos = null;
        try {
            gis = new GZIPInputStream(is);
            fos = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuitely(gis);
            closeQuitely(fos);
        }
    }


    /**
     * gzip解压入口
     */
    public static void deTarFile(String destPath, TarArchiveInputStream tais) throws Exception {

        TarArchiveEntry tae = null;
        while ((tae = tais.getNextTarEntry()) != null) {

            String dir = destPath + File.separator + tae.getName();//tar档中文件
            File dirFile = new File(dir);
            try {
                if (tae.isDirectory()) {
                    if (dirFile.exists()) {
                        if (!dirFile.isDirectory()) {
                            dirFile.mkdir();
                        }
                    } else {
                        dirFile.mkdir();
                    }
                } else if (!dirFile.exists()) {
                    dirFile.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!tae.isDirectory()) {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dirFile));

                int count;
                byte data[] = new byte[1024];
                while ((count = tais.read(data, 0, 1024)) != -1) {
                    bos.write(data, 0, count);
                }

                bos.close();
            }

        }
    }

}