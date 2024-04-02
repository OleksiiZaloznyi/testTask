//package com.example.bookstore.server;
//
//import java.io.IOException;
//import com.example.bookstore.service.BookstoreServiceImpl;
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import org.springframework.stereotype.Component;
//
//public class BookstoreServer {
//    private final int port;
//    private Server server;
//
//    public BookstoreServer(int port) {
//        this.port = port;
//        this.server = ServerBuilder
//                .forPort(port)
//                .addService(new BookstoreServiceImpl())
//                .build();
//    }
//
//    public void start() throws IOException {
//        server.start();
//        System.out.println("Server started, listening on " + port);
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.err.println("*** shutting down gRPC server since JVM is shutting down");
//            BookstoreServer.this.stop();
//            System.err.println("*** server shut down");
//        }));
//    }
//
//    public void stop() {
//        if (server != null) {
//            server.shutdown();
//        }
//    }
//
//    public void blockUntilShutdown() throws InterruptedException {
//        if (server != null) {
//            server.awaitTermination();
//        }
//    }
//}
