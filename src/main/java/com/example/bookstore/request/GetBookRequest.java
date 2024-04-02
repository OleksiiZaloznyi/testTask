package com.example.bookstore.request;

import java.util.UUID;
import lombok.Data;

@Data
public class GetBookRequest {
    private UUID id;
}
