package danrusso.U5_W2_D3_Esercizio.payloads;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record NewBlogPostDTO(
        @Size(min = 4, max = 20, message = "Name's lenght must be between 4 and 20.")
        String category,
        @NotBlank(message = "Title is mandatory.")
        String title,
        @NotBlank(message = "Content is mandatory.")
        @Size(min = 20, max = 300, message = "Content's lenght must be between 20 and 300.")
        String content,
        @NotNull(message = "Reading time is mandatory.")
        @Min(value = 10, message = "Reading time can't be under 10 minutes")
        @Max(value = 500, message = "Reading time can't be over 500 minutes.")
        int readingTime,
        @NotNull(message = "AuthorId is mandatory.")
        UUID authorId
) {
}
