package ru.inno.sql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.ipc.netty.NettyContext;
import ru.inno.sql.core.config.AppConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            context.getBean(NettyContext.class).onClose().block();
        }
    }
}
