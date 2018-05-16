package ru.inno.sql.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.ipc.netty.NettyContext;
import ru.inno.sql.core.server.Server;

@Configuration
@EnableWebFlux
@ComponentScan("ru.inno.sql")
@PropertySource(value={"classpath:application.properties"})
public class AppConfig extends WebFluxConfigurationSupport {

    @Value("${server.port}")
    private Integer serverPort;

    public static final String HOST = "localhost";

    private Server server;

    public Integer getServerPort() {
        return serverPort;
    }

    @Bean
    public NettyContext nettyContext(ApplicationContext context) {
        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context)
                .build();
        return server.startReactorServer(HOST, serverPort, handler);
    }

    @Autowired
    public void setServer(Server server) {
        this.server = server;
    }
}
