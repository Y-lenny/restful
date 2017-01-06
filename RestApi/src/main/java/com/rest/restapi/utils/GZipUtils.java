package com.rest.restapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP工具
 * 
 */
public abstract class GZipUtils {
    private static final Logger logger = LoggerFactory.getLogger(GZipUtils.class);
    public static final int BUFFER = 1024;
    public static final String EXT = ".gz";

    /**
     * 数据压缩
     * 
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static byte[] compress(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 压缩
        compress(bais, baos);

        byte[] output = baos.toByteArray();

        try {
            baos.flush();
        } catch (IOException e2) {
            //e2.printStackTrace();
            logger.error("data compress failed when flushing ", e2);
        }

        try {
            baos.close();
        } catch (IOException e1) {
            //e1.printStackTrace();
            logger.error("data compress InputStream close failed ", e1);
        }

        try {
            bais.close();
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("data compress OutputStream close failed ", e);
        }

        return output;
    }

    /**
     * 文件压缩(压缩后删除源文件)
     * 
     * @param file 待压文件
     * @throws Exception
     */
    public static void compress(File file) {
        compress(file, true);
    }

    /**
     * 文件压缩
     * 
     * @param file 待压文件
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void compress(File file, boolean delete) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file.getPath() + EXT);
            compress(fis, fos);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            logger.error("compress fail :"+e);
        } finally {
            try {
                if (fis != null) {
                    fis.close(); 
                }
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error("File compress InputStream close failed ", e);
            }

            try {
                if (fos != null) {
                    fos.flush();
                }
            } catch (IOException e1) {
                //e1.printStackTrace();
                logger.error("File compress OutputStream flush failed ", e1);
                
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error("File compress OutputStream close failed ", e);
                
            }

        }

        if (delete) {
            boolean flag = file.delete();
            if(flag == false){
                //System.out.println("Delete error");
                logger.error("file Delete error!");
            }
        }
    }

    /**
     * 数据压缩
     * 
     * @param is
     * @param os
     * @throws IOException
     * @throws Exception
     */
    public static void compress(InputStream is, OutputStream os) {
        GZIPOutputStream gos = null;
        try {
            gos = new GZIPOutputStream(os);

            int count;
            byte data[] = new byte[BUFFER];
            while ((count = is.read(data, 0, BUFFER)) != -1) {
                gos.write(data, 0, count);
            }

            gos.finish();
            gos.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("data compress failed ",e);
        } finally {
            try {
                if (gos != null) {
                    gos.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error("GZIPOutputStream closed failed ",e);
            }
        }
    }

    /**
     * 文件压缩(压缩后删除源文件)
     * 
     * @param path 待压文件路径
     * @throws Exception
     */
    public static void compress(String path) {
        compress(path, true);
    }

    /**
     * 文件压缩
     * 
     * @param path 待压文件路径
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void compress(String path, boolean delete) {
        File file = new File(path);
        compress(file, delete);
    }

    /**
     * 数据解压缩
     * 
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static byte[] decompress(byte[] data) {
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        try {
            bais = new ByteArrayInputStream(data);
            baos = new ByteArrayOutputStream();

            // 解压缩
            decompress(bais, baos);
            data = baos.toByteArray();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                }
            } catch (IOException e2) {
                //e2.printStackTrace();
                logger.error("data decompress failed when flushing ", e2);
            }
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e1) {
                //e1.printStackTrace();
                logger.error("data compress OutputStream close failed ", e1);
            } 

            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error("data compress InputStream close failed ", e);
            }
        }

        return data;
    }

    /**
     * 文件解压缩
     * 
     * @param file 待解压文件
     * @throws Exception
     */
    public static void decompress(File file) {
        decompress(file, true);
    }

    /**
     * 文件解压缩
     * 
     * @param file 待解压文件
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void decompress(File file, boolean delete) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file.getPath().replace(EXT, ""));
            decompress(fis, fos);
        } catch (FileNotFoundException e2) {
            //e2.printStackTrace();
            logger.error("decompress fail :"+e2);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error("File decompress InputStream close failed ", e);
            }

            try {
                if (fos != null) {
                    fos.flush();
                }
            } catch (IOException e1) {
                //e1.printStackTrace();
                logger.error("File decompress OutputStream flush failed ", e1);
            }
            try {
                if (fos != null) {
                    fos.close(); 
                }
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error("File decompress OutputStream close failed ", e);
            }
        }

        if (delete) {
            boolean flag = file.delete();
            if(flag == false){
                //System.out.println("Delete error");
                logger.error("File Delete Error");
            }
        }
    }

    /**
     * 数据解压缩
     * 
     * @param is
     * @param os
     * @throws IOException
     * @throws Exception
     */
    public static void decompress(InputStream is, OutputStream os) {
        GZIPInputStream gis = null;
        try {
            gis = new GZIPInputStream(is);

            int count;
            byte data[] = new byte[BUFFER];
            while ((count = gis.read(data, 0, BUFFER)) != -1) {
                os.write(data, 0, count);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            logger.error("data decompress failed ",e);
        } finally {
            try {
                if (gis != null) {
                    gis.close();
                }
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error("data decompress GZIPInputStream close fail ",e);
            }
        }
    }

    /**
     * 文件解压缩(解压完删掉源文件)
     * 
     * @param path 待解压文件路径
     * @throws Exception
     */
    public static void decompress(String path) {
        decompress(path, true);
    }

    /**
     * 文件解压缩
     * 
     * @param path 待解压文件路径
     * @param delete 是否删除原始文件
     * @throws Exception
     */
    public static void decompress(String path, boolean delete) {
        File file = new File(path);
        decompress(file, delete);
    }

}
