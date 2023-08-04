package com.example.httpintegration.models;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ToDo {
    private String id;
    private String description;
    private LocalDateTime created;
    private boolean completed;

    public ToDo() {
        this.id = UUID.randomUUID().toString();
        this.created = LocalDateTime.now();
    }

    public ToDo(String description) {
        this();
        this.description = description;
    }

    public ToDo(String description, boolean completed) {
        this(description);
        this.completed = completed;
    }
}
