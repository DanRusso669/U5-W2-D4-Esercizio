package danrusso.U5_W2_D3_Esercizio.payloads;

import danrusso.U5_W2_D3_Esercizio.entities.Author;

public record NewBlogPostRespDTO(String category, String title, String content, int readingTime, Author author) {
}
