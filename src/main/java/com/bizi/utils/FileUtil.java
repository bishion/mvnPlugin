package com.bizi.utils;

import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by guofangbi on 2017/3/17.
 */
public abstract class FileUtil {
    public static void writeContentToFile(Log log,String content,String targetPath,Map<String,String> map){
        if(map!=null && map.size()>0){
            for (String key : map.keySet()){
                content = content.replaceAll(key,map.get(key));
            }
        }

        File file = new File(targetPath);

        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(targetPath);
            fos.write(content.getBytes());
            fos.close();
            log.info("创建文件成功--->"+targetPath);
        } catch (IOException e) {
            log.error("创建文件失败--->"+targetPath,e);
        }
    }
}
