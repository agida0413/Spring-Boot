package hello.embeded;

import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbedTomcatSpringMain {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(HelloConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);

        Context context = tomcat.addContext("", "/");
        tomcat.addServlet("","dispatcher",dispatcherServlet);
        context.addServletMappingDecoded("/","dispatcher");
        tomcat.start();
    }
}
