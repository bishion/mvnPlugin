package com.bizi.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Created by guofangbi on 2017/3/16.
 * 最简单的插件，在控制台打印Hello World
 * 执行：mvn bizi:first
 */
@Mojo(name = "first")
public class First extends AbstractMojo{
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("Hello world!");
        getLog().info("this is a log ：Hello World!");
    }
}
