package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV2Spring implements AppInit{
    @Override
    public void onStartUp(ServletContext servletContext) {
        System.out.println("AppInitV2Spring.onStartUp");

        //스프링 컨테이너

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        //디스패처
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        ServletRegistration.Dynamic dispatcherV2 = servletContext.addServlet("dispatcherV2", dispatcherServlet);

        dispatcherV2.addMapping("/spring/*");

    }
}
