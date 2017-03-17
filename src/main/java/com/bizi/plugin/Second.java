package com.bizi.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Created by guofangbi on 2017/3/16.
 * 读取传入的参数，如果不传，则给它默认值
 * 执行：mvn bizi:second -Dport=***
 */
@Mojo(name = "second")
public class Second extends AbstractMojo {
    @Parameter(property="port", defaultValue = "8080")
    private String port;
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("port is :"+port);
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
