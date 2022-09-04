package com.tenneling;

import com.tenneling.utils.SpringContextUtils;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.tenneling.entity")
@MapperScan(basePackages ="com.tenneling.dao")
@EnableScheduling
@EnableRabbit  //开启基于注解的rabbit模式
public class TennelingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TennelingApplication.class, args);
        SpringContextUtils.setApplicationContext(applicationContext);
    }

    //SpringBoot-v2.0+配置方式
    @Bean
    public ServletWebServerFactory servletContainer() {
        //创建Tomcat服务器工厂实例
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };;
        //添加此tomcat实例其它连接参数
        tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
        return tomcat;
    }

    /**
     * 配置tomcat自定义连接参数
     * @return
     */
    private Connector createHTTPConnector() {
        //Connector port有两种运行模式(NIO和APR)，选择NIO模式：protocol="org.apache.coyote.http11.Http11NioProtocol"
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        //同时启用http（8080）、https（8443）两个端口
        connector.setScheme("http");
        //设置安全连接标志，该标志将被分配给通过该连接接收的请求
        //secure新的安全连接标志
        //如果connector.setSecure(true)，则http使用http, https使用https; 分离状态，因此设置false
        connector.setSecure(false);
        //http默认端口
        connector.setPort(9091);
        //重定向证书端口443，便于http自动跳转https
        connector.setRedirectPort(9092);
        connector.setURIEncoding("utf-8");
        connector.setProperty("relaxedQueryChars", "|{}[],%");
        connector.setProperty("relaxedPathChars", "|{}[],%");
        return connector;
    }
}
