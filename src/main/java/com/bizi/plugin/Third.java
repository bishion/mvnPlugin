package com.bizi.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.io.IOException;

/**
 * Created by guofangbi on 2017/3/16.
 * 操作文件
 */
@Mojo(name = "third")
public class Third extends AbstractMojo{
    public void execute() throws MojoExecutionException, MojoFailureException {
        File file = new File("config");
        if(file.exists()){
            File[] fileList = file.listFiles();
            System.out.println("文件列表如下：");
            for (File temFile : fileList){
                System.out.println(temFile.getName());
            }
        }else{
            file.mkdir();
            System.out.println("新建了config文件夹");
        }
    }
}
