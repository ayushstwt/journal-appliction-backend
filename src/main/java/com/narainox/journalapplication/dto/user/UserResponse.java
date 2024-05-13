package com.narainox.journalapplication.dto.user;

import com.narainox.journalapplication.entity.JournalEntry;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private List<JournalEntry> journalEntryList=new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
