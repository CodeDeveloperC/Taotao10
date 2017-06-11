package com.jumi.redantsoft;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.JreMemoryLeakPreventionListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class JumiTomcatBootstrap {
    private static final Logger LOG = LoggerFactory.getLogger(JumiTomcatBootstrap.class);

    public static void main(String[] args) throws Exception {
        //提升性能(https://wiki.apache.org/tomcat/HowTo/FasterStartUp)
        //System.setProperty("tomcat.util.scan.StandardJarScanFilter.jarsToSkip", "*.jar");
        //System.setProperty("securerandom.source","file:/dev/./urandom");
        int port = Integer.parseInt(System.getProperty("server.port", "8020"));
        String contextPath = System.getProperty("server.contextPath", "");
        String docBase = System.getProperty("server.docBase", getDefaultDocBase());
        //LOG.info("server port : {}, context path : {},doc base : {}", port, contextPath, docBase);
        final Tomcat tomcat = createTomcat(port, contextPath, docBase);
        tomcat.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    tomcat.stop();
                } catch (LifecycleException e) {
                    LOG.error("stoptomcat error.", e);
                }
            }
        });
        tomcat.getServer().await();
    }

    private static String getDefaultDocBase() {

        System.err.println(System.getProperty("server.docBase"));
        try {
            File classpathDir = new File(Thread.currentThread().getContextClassLoader().getResource(".").getFile());
            File projectDir = classpathDir.getParentFile().getParentFile();
            return URLDecoder.decode(new File(projectDir, "src/main/webapp").getPath(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";// " D:/DEV/eclipse-jee-mars/淘宝产品�?/vancloud-huaduo/src/main/webapp";
    }

    private static Tomcat createTomcat(int port, String contextPath, String docBase) throws Exception {
        String tmpdir = System.getProperty("java.io.tmpdir");
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(tmpdir);
        tomcat.getHost().setAppBase(tmpdir);
        tomcat.getHost().setAutoDeploy(false);
        tomcat.getHost().setDeployOnStartup(false);
        tomcat.getEngine().setBackgroundProcessorDelay(-1);
        tomcat.setConnector(newNioConnector());
        tomcat.getConnector().setPort(port);
        //http://tomcat.apache.org/tomcat-7.0-doc/config/http.html
        //tomcat.getConnector().setAttribute("maxThreads", "200");
        tomcat.getService().addConnector(tomcat.getConnector());
        //tomcat.addWebapp(contextPath, docBase);
        System.err.println(docBase);
        Context context = tomcat.addWebapp(contextPath, docBase);
        //ObjectName name = new ObjectName("Tomcat", "type", "Server");
        //Server server = (Server) mBeanServer.getAttribute(name, "managedResource");
        StandardServer server = (StandardServer) tomcat.getServer();
        //APR library loader. Documentation at /docs/apr.html
        server.addLifecycleListener(new AprLifecycleListener());
        //Prevent memory leaks due to use of particularjava/javax APIs
        server.addLifecycleListener(new JreMemoryLeakPreventionListener());
        //JmxRemoteLifecycleListener
        //server.addLifecycleListener(new JmxRemoteLifecycleListener());
        return tomcat;
    }

    //在这里调整参数优�?
    private static Connector newNioConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setAttribute("maxThreads", "200"); //
        connector.setAttribute("connectionTimeout", "60000");
        //Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        return connector;
    }
}
