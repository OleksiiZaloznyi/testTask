package com.example.bookstore.service;

import java.util.UUID;
import com.example.bookstore.BookstoreProto;
import com.example.bookstore.BookstoreServiceGrpc;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookstoreServiceImpl extends BookstoreServiceGrpc.BookstoreServiceImplBase {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public void addBook(BookstoreProto.Book request, StreamObserver<BookstoreProto.Book> responseObserver) {
        BookDto bookDto = new BookDto(
                request.getId(),
                request.getTitle(),
                request.getAuthor(),
                request.getIsbn(),
                request.getQuantity()
        );

        Book book = bookMapper.bookDtoToBook(bookDto);
        Book savedBook = bookRepository.save(book);
        BookstoreProto.Book response = bookMapper.bookToBookProto(savedBook);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getBook(BookstoreProto.GetBookRequest request, StreamObserver<BookstoreProto.Book> responseObserver) {
        Book book = bookRepository.findById(UUID.fromString(request.getId())).orElse(null);

        if (book != null) {
            BookstoreProto.Book response = bookMapper.bookToBookProto(book);
            responseObserver.onNext(response);
        } else {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Book with id " + request.getId() + " not found")
                    .asRuntimeException());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void updateBook(BookstoreProto.Book request, StreamObserver<BookstoreProto.Book> responseObserver) {
        Book existingBook = bookRepository.findById(UUID.fromString(request.getId())).orElse(null);

        if (existingBook != null) {
            BookDto bookDto = bookMapper.bookToBookDto(existingBook);
            bookDto.setTitle(request.getTitle());
            bookDto.setAuthor(request.getAuthor());
            bookDto.setIsbn(request.getIsbn());
            bookDto.setQuantity(request.getQuantity());

            Book updatedBook = bookMapper.bookDtoToBook(bookDto);
            Book savedBook = bookRepository.save(updatedBook);
            BookstoreProto.Book response = bookMapper.bookToBookProto(savedBook);
            responseObserver.onNext(response);
        } else {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Book with id " + request.getId() + " not found")
                    .asRuntimeException());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void deleteBook(BookstoreProto.DeleteBookRequest request, StreamObserver<BookstoreProto.Book> responseObserver) {
        Book book = bookRepository.findById(UUID.fromString(request.getId())).orElse(null);

        if (book != null) {
            bookRepository.delete(book);
            BookstoreProto.Book response = bookMapper.bookToBookProto(book);
            responseObserver.onNext(response);
        } else {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Book with id " + request.getId() + " not found")
                    .asRuntimeException());
        }
        responseObserver.onCompleted();
    }
}
