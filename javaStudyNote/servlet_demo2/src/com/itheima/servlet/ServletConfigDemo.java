package com.itheima.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
* ServletConfig的演示
* */
public class ServletConfigDemo extends HttpServlet {
    //1.声明ServletConfig
    private ServletConfig config;

    //2.通过init方法，来对我们的ServletConfig对象进行赋值
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config=config;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //3.演示ServletConfig常用方法
        //根据key来获取value的值
        String encodingValue = config.getInitParameter("encoding");
        System.out.println(encodingValue);

        Enumeration<String> keys = config.getInitParameterNames();
        while (keys.hasMoreElements()){
            //获取每一个key
            String key = keys.nextElement();
            //根据key获取value
            String value = config.getInitParameter(key);

            System.out.println(key + "," + value);

            //获取Servlet的名称
            String servletName = config.getServletName();
            System.out.println(servletName);

            //获取ServletContext
            ServletContext servletContext = config.getServletContext();
            System.out.println(servletContext);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
