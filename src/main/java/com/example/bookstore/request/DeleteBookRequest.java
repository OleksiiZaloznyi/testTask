package com.example.bookstore.request;

import java.util.UUID;
import lombok.Data;

@Data
public class DeleteBookRequest {
    private UUID id;
}
