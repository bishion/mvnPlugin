package com.bizi.plugin;

import com.bizi.utils.FileUtil;
import net.wicp.tams.commons.apiext.IOUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guofangbi on 2017/3/17.
 * 文件替换，用于自动生成代码
 */
@Mojo(name = "forth")
public class Forth extends AbstractMojo{
    @Parameter(property = "groupId",defaultValue = "com.bizi")
    private String groupId;
    @Parameter(property = "artifactId",defaultValue = "test")
    private String artifactId;
    public void execute() throws MojoExecutionException, MojoFailureException {
        String basePackage = groupId+"."+artifactId;
        String baseDir = basePackage.replaceAll("\\.","/");
        Map<String,String> map = new HashMap<>(2);
        map.put("[projectName]",artifactId);
        map.put("[basepackage]",basePackage);
        try {
            System.out.println(getClass().getResource("/init").toURI());
            String initPath = getClass().getResource("/init").getPath();

            Map<String,InputStream> copyFilesMap = IOUtil.getFilesFromJar("jar:"+initPath,"init");
            for (String fileName : copyFilesMap.keySet()){
                String content = IOUtil.slurp(copyFilesMap.get(fileName));
                String targetPath ;
                int lastIndex = fileName.lastIndexOf("/");
                int firstIndex = fileName.indexOf("/")+1;
                String singleFileName = fileName.substring(lastIndex);
                if(fileName.endsWith(".java")){
                    targetPath =fileName.substring(firstIndex,lastIndex)+"/"+baseDir+singleFileName;
                }else {
                    targetPath = fileName.substring(firstIndex);
                }
                FileUtil.writeContentToFile(getLog(),content,artifactId+"/"+targetPath,map);
            }

        } catch (Exception e) {
            e.printStackTrace();
            getLog().error("初始化失败",e);
            throw new MojoFailureException("初始化失败",e);
        }
    }

    public static void main(String[] args) {
        String basePackage = "com.bizi.test";
        String baseDir = basePackage.replaceAll("\\.","/");
        String fileName = "init/src/main/java/application.java";
        String targetPath ;
        int lastIndex = fileName.lastIndexOf("/");
        int firstIndex = fileName.indexOf("/");
//                String relativePath = fileName.substring(fileName.indexOf("/"),lastIndexOf);
                String singleFileName = fileName.substring(lastIndex);
        if(fileName.endsWith(".java")){
            targetPath =fileName.substring(firstIndex,lastIndex)+"/"+baseDir+singleFileName;
        System.out.println(targetPath);
        }else {
            targetPath = fileName.substring(firstIndex+1);
        System.out.println(targetPath);
        }
    }

}
