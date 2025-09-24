package com.lc.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RunConfig implements CommandLineRunner, WebMvcConfigurer {

    @Value("${openProject.isOpen}")
    private boolean isOpen;

    @Value("${openProject.web.openUrl}")
    private String openUrl;

    @Value("${openProject.cmd}")
    private String cmd;

    @Override
    public void run(String... args) {
        if (isOpen) {
            String runCmd = cmd + " " + openUrl;
            System.out.println("运行的命令: " + runCmd);
            Runtime run = Runtime.getRuntime();
            try {
                run.exec(runCmd);
                System.out.println("启动浏览器打开项目成功");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("启动项目自动打开浏览器失败");
            }
        }
    }
}