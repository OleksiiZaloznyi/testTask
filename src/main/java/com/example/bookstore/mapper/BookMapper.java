package com.example.bookstore.mapper;

import com.example.bookstore.BookstoreProto;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", expression = "java(book.getId().toString())")
    BookDto bookToBookDto(Book book);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(bookDto.getId()))")
    Book bookDtoToBook(BookDto bookDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "quantity", source = "quantity")
    BookstoreProto.Book bookToBookProto(Book book);

    @Mapping(target = "id", expression = "java(java.util.UUID.fromString(book.getId()))")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "quantity", source = "quantity")
    Book bookProtoToBook(BookstoreProto.Book book);
}
