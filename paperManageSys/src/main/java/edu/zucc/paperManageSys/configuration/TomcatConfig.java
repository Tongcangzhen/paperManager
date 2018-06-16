package edu.zucc.paperManageSys.configuration;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;
import java.io.File;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication
public class TomcatConfig {
    @Configuration
    @ConditionalOnClass({ Servlet.class, Tomcat.class })
    @ConditionalOnMissingBean(value = TomcatServletWebServerFactory.class, search = SearchStrategy.CURRENT)
    public static class EmbeddedTomcat {
        @Bean
        public ServletWebServerFactory tomcatEmbeddedServletContainerFactory() {
            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//            tomcat.setBaseDirectory(new File("/files"));
            return tomcat;
        }

        @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            // 设置文件大小限制 ,超出设置页面会抛出异常信息，
            // 这样在文件上传的地方就需要进行异常信息的处理了;
            factory.setMaxFileSize("20MB"); // KB,MB
            /// 设置总上传数据总大小
            //factory.setMaxRequestSize("512KB");
             //Sets the directory location where files will be stored.
//             factory.setLocation("/");
            return factory.createMultipartConfig();
        }
    }
}
