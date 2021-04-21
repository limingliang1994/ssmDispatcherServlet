package com.mliang.mvcframework.v2.servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GPDispatcherServlet extends HttpServlet {
    private Properties contextConfig = new Properties();
    private List<String> classNames = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch();
    }

    private void doDispatch() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        //3.实例化
        doInstance();
        //4.完成依赖注入
        doAutowied();
        //5.初始化HanderMapping
        doInitHandlerMapping();
        System.out.println("初始化完成");
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is =  this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/"+ scanPackage.replaceAll(".","/"));
        File classPath = new File(url.getFile());
        for (File file: classPath.listFiles()) {
            //拿到类名后通过反射创建类的对象
            String className =scanPackage+"."+ file.getName().replace(".class","");
            classNames.add(className);
        }
    }

    private void doInstance() {
    }

    private void doAutowied() {
    }

    private void doInitHandlerMapping() {
    }


}
