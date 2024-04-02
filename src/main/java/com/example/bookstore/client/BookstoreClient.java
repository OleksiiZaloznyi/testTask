//package com.example.bookstore.client;
//
//import java.util.UUID;
//import com.example.bookstore.BookstoreServiceGrpc;
//import com.example.bookstore.BookstoreProto.Book;
//import com.example.bookstore.request.DeleteBookRequest;
//import com.example.bookstore.request.GetBookRequest;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//
//public class BookstoreClient {
//    private final ManagedChannel channel;
//    private final BookstoreServiceGrpc.BookstoreServiceBlockingStub blockingStub;
//
//    public BookstoreClient(String host, int port) {
//        this.channel = ManagedChannelBuilder.forAddress(host, port)
//                .usePlaintext()
//                .build();
//        this.blockingStub = BookstoreServiceGrpc.newBlockingStub(channel);
//    }
//
//    public Book addBook(Book book) {
//        return blockingStub.addBook(book);
//    }
//
//    public Book getBook(UUID id) {
//        return blockingStub.getBook(GetBookRequest.newBuilder().setId(id).build());
//    }
//
//    public Book updateBook(Book book) {
//        return blockingStub.updateBook(book);
//    }
//
//    public Book deleteBook(UUID id) {
//        return blockingStub.deleteBook(DeleteBookRequest.newBuilder().setId(id).build());
//    }
//}
