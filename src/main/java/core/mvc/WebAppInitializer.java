package core.mvc;

import core.di.factory.BasePackageScanner;
import core.di.factory.BeanFactory;
import core.di.factory.BeanScanner;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        BasePackageScanner basePackageScanner = new BasePackageScanner();
        BeanScanner beanScanner = new BeanScanner(basePackageScanner.getBasePackage());
        BeanFactory beanFactory = new BeanFactory(beanScanner.getPreInstanticateBeans());

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(beanFactory));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}