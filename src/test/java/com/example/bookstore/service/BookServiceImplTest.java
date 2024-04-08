package com.example.bookstore.service;

import com.example.bookstore.BookstoreProto;
import com.example.bookstore.BookstoreServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class BookServiceImplTest {

    @Container
    public static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test_bookstore")
            .withUsername("postgres")
            .withPassword("12345");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    private static ManagedChannel channel;
    private static BookstoreServiceGrpc.BookstoreServiceBlockingStub blockingStub;

    @LocalServerPort
    private static int serverPort;

    @AfterEach
    void tearDown() {
        if (channel != null) {
            channel.shutdownNow();
        }
    }

    @BeforeAll
    static void init() {
        String target = "localhost:" + serverPort;
        channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build();
        blockingStub = BookstoreServiceGrpc.newBlockingStub(channel);
    }

//    @Test
//    public void testAddBook() {
//        BookstoreProto.Book request = BookstoreProto.Book.newBuilder()
//                .setId("123e4567-e89b-12d3-a456-426614174000")
//                .setTitle("Effective Java")
//                .setAuthor("Joshua Bloch")
//                .setIsbn("12344321")
//                .build();
//
//        BookstoreProto.Book response = blockingStub.addBook(request);
//
//        assertEquals("123e4567-e89b-12d3-a456-426614174000", response.getId());
//        assertEquals("Effective Java", response.getTitle());
//        assertEquals("Joshua Bloch", response.getAuthor());
//        assertEquals("12344321", response.getIsbn());
//    }
//
//    @Test
//    public void testGetBook() throws Exception {
//        String expectedId = "123e4567-e89b-12d3-a456-426614174000";
//        String title = "Effective Java";
//        String author = "Joshua Bloch";
//        String isbn = "12344321";
//
//        BookstoreProto.Book request = BookstoreProto.Book.newBuilder()
//                .setId(expectedId)
//                .setTitle(title)
//                .setAuthor(author)
//                .setIsbn(isbn)
//                .build();
//        blockingStub.addBook(request);
//
//        BookstoreProto.GetBookRequest getBookRequest = BookstoreProto.GetBookRequest.newBuilder()
//                .setId(expectedId)
//                .build();
//
//        BookstoreProto.Book response = blockingStub.getBook(getBookRequest);
//
//        assertEquals(expectedId, response.getId());
//        assertEquals(title, response.getTitle());
//        assertEquals(author, response.getAuthor());
//        assertEquals(isbn, response.getIsbn());
//    }
//
//    @Test
//    public void testUpdateBook() throws Exception {
//        // Prepare data for adding a book (similar to testGetBook)
//        String expectedId = "123e4567-e89b-12d3-a456-426614174000";
//        String title = "Effective Java";
//        String author = "Joshua Bloch";
//        String isbn = "12344321";
//
//        // Add a book using the tested addBook method
//        BookstoreProto.Book request = BookstoreProto.Book.newBuilder()
//                .setId(expectedId)
//                .setTitle(title)
//                .setAuthor(author)
//                .setIsbn(isbn)
//                .build();
//        blockingStub.addBook(request);
//
//        // Prepare update data (updated title, author, and maybe ISBN)
//        String updatedTitle = "Clean Code";
//        String updatedAuthor = "Robert C. Martin";
//        String updatedIsbn = "9780136699610";
//
//        // Simulate updateBook request with the added book's ID and updated data
//        BookstoreProto.Book updateRequest = BookstoreProto.Book.newBuilder()
//                .setId(expectedId)
//                .setTitle(updatedTitle)
//                .setAuthor(updatedAuthor)
//                .setIsbn(updatedIsbn)
//                .build();
//
//        // Call the gRPC method
//        BookstoreProto.Book response = blockingStub.updateBook(updateRequest);
//
//        // Verify response (updated data should be reflected)
//        assertEquals(expectedId, response.getId());
//        assertEquals(updatedTitle, response.getTitle());
//        assertEquals(updatedAuthor, response.getAuthor());
//        assertEquals(updatedIsbn, response.getIsbn());
//    }
//
//    @Test
//    public void testDeleteBook() throws Exception {
//        // Prepare data for adding a book (similar to testGetBook)
//        String expectedId = "123e4567-e89b-12d3-a456-426614174000";
//        String title = "Effective Java";
//        String author = "Joshua Bloch";
//        String isbn = "12344321";
//
//        // Add a book using the tested addBook method
//        BookstoreProto.Book request = BookstoreProto.Book.newBuilder()
//                .setId(expectedId)
//                .setTitle(title)
//                .setAuthor(author)
//                .setIsbn(isbn)
//                .build();
//        blockingStub.addBook(request);
//
//        // Simulate deleteBook request with the added book's ID
//        BookstoreProto.DeleteBookRequest deleteRequest = BookstoreProto.DeleteBookRequest.newBuilder()
//                .setId(expectedId)
//                .build();
//
//        // Call the gRPC method
//        BookstoreProto.Book response = blockingStub.deleteBook(deleteRequest);
//
//        // Verify response (deleted book returned)
//        assertEquals(expectedId, response.getId());
//        assertEquals(title, response.getTitle());
//        assertEquals(author, response.getAuthor());
//        assertEquals(isbn, response.getIsbn());
//    }
}