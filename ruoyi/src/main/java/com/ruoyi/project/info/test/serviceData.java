package com.ruoyi.project.info.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Component("serviceTask")
public class serviceData {
    //启动服务
    public void startup(){
        try{
            File dir = new File("D:\\");
            String command = "D:\\startup.bat";
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command, null, dir);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String inline;
            while (null != (inline = br.readLine())) {
                sb.append(inline).append("\n");
            }
            System.out.println(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //关闭服务
    public void shutdown(){
        try{
            File dir = new File("D:\\");
            String command = "D:\\shutdown.bat";
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(command, null, dir);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String inline;
            while (null != (inline = br.readLine())) {
                sb.append(inline).append("\n");
            }
            System.out.println(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
