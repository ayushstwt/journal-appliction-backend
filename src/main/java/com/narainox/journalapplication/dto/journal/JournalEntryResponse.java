package com.narainox.journalapplication.dto.journal;

import com.narainox.journalapplication.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class JournalEntryResponse {
    private Integer id;
    private String content;
    private String title;
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
