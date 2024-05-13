package com.narainox.journalapplication.dto.journal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JournalEntryRequest {
    @NotBlank(message = "content is required parameter.")
    private String content;
    @NotBlank(message = "title is required parameter.")
    private String title;

}
