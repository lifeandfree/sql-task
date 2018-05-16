package ru.inno.sql.core.server;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.stereotype.Service;
import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

@Service
public class ServerImpl implements Server {

    @Override
    public NettyContext startReactorServer(String host, int port, HttpHandler httpHandler) {
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create(host, port);
        return server.newHandler(adapter).block();
    }
}