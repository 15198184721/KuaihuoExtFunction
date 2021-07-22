package com.kuaihuo.ext.components.https;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * https相关的支持配置
 * 1、需要秘钥:
 * sample.jks
 *
 * 2、需要application.yml配置：
 * https:
 * port: 8443
 * ssl:
 * key-store: classpath:sample.jks
 * key-store-password: secret
 * key-password: password
 * 3、此类的配置注入到Spring容器
 */
@Component
public class HttpsSSLBeans {

    @Value("${server.port}")
    private Integer httpPort; //http 的默认端口

    @Value("${https.port}")
    private Integer httpsPort; //https的默认端口

    @Value("${https.ssl.key-store-password}")
    private String key_store_password;

    @Value("${https.ssl.key-password}")
    private String key_password;

    /* --------------------请按照自己spring boot版本选择 start--------------------- */

    //配置http，主要是为了让https代理所有的http请求配置的
    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

        connector.setScheme("http");
        //Connector监听的http的默认端口号
        connector.setPort(httpPort);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号,也就是项目配置的port
        connector.setRedirectPort(httpsPort);
        return connector;
    }

    /* ------------------- https的支持配置 ---------------------- */
    // 这是spring boot 2.0.X版本的 添加这个，上一个就不用添加了
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加http
        return tomcat;
    }

    // 配置https
    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File keystore = new ClassPathResource("tomcat_https.jks").getFile();
            /*File truststore = new ClassPathResource("sample.jks").getFile();*/
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setPort(httpsPort);
            protocol.setSSLEnabled(true);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass(key_store_password);
            protocol.setKeyPass(key_password);
            return connector;
        } catch (IOException ex) {
            throw new IllegalStateException("can't access keystore: [" + "keystore"
                    + "] or truststore: [" + "keystore" + "]", ex);
        }
    }

}
