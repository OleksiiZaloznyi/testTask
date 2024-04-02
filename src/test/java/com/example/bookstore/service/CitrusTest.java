//package com.example.bookstore.service;
//
//import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
//import com.example.bookstore.BookstoreProto;
//import com.example.bookstore.BookstoreServiceGrpc;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.UUID;
//
//
//public class CitrusTest extends TestNGCitrusTestDesigner {
//
//    private static ManagedChannel channel;
//    private static BookstoreServiceGrpc.BookstoreServiceBlockingStub blockingStub;
//
//    @Autowired
//    private BookstoreServiceGrpc.BookstoreServiceBlockingStub bookstoreService;
//
//    @BeforeClass
//    public void setUp() {
//        // Создание канала для взаимодействия с gRPC-сервисом
//        String target = "localhost:" + 9090;
//        channel = ManagedChannelBuilder.forTarget(target)
//                .usePlaintext()
//                .build();
//        blockingStub = BookstoreServiceGrpc.newBlockingStub(channel);
//    }
//
//    @AfterClass
//    public void tearDown() {
//        // Закрытие канала после выполнения всех тестов
//        channel.shutdown();
//    }
//
//    @Test
//    @com.consol.citrus.annotations.CitrusTest
//    public void testAddBook() {
//        // Подготовка запроса на добавление книги
//        BookstoreProto.Book request = BookstoreProto.Book.newBuilder()
//                .setId(UUID.randomUUID().toString())
//                .setTitle("Test Book")
//                .setAuthor("Test Author")
//                .setIsbn("1234567890")
//                .setQuantity(1)
//                .build();
//
//        // Вызов gRPC метода и получение ответа
//        BookstoreProto.Book response = blockingStub.addBook(request);
//
//        // Проверка успешности добавления книги
//        assertAll(
//                () -> assertEquals(request.getId(), response.getId()),
//                () -> assertEquals(request.getTitle(), response.getTitle()),
//                () -> assertEquals(request.getAuthor(), response.getAuthor()),
//                () -> assertEquals(request.getIsbn(), response.getIsbn())
//        );
//    }
//
//    // Другие тесты для остальных методов
//}
