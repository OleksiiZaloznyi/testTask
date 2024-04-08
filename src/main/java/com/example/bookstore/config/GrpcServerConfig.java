package com.example.bookstore.config;

import com.example.bookstore.service.BookstoreServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
public class GrpcServerConfig {

    private final BookstoreServiceImpl bookstoreService;
    private final Environment environment;

    public GrpcServerConfig(BookstoreServiceImpl bookstoreService, Environment environment) {
        this.bookstoreService = bookstoreService;
        this.environment = environment;

    }

    @Bean
    public SmartLifecycle grpcServerLifecycle() {
        return new SmartLifecycle() {
            private Server server;
            private boolean isRunning = false;

            @Override
            public void start() {
                int grpcPort = environment.getProperty("grpc.server.port", Integer.class, 9090);
                server = ServerBuilder.forPort(grpcPort)
                        .addService(bookstoreService)
                        .build();
                try {
                    server.start();
                    int actualPort = server.getPort();
                    log.info("Server started, listening on " + actualPort);
                    isRunning = true;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void stop() {
                if (server != null) {
                    server.shutdown();
                }
                isRunning = false;
            }

            @Override
            public boolean isRunning() {
                return isRunning;
            }

            @Override
            public int getPhase() {
                return Integer.MAX_VALUE;
            }
        };
    }
}
