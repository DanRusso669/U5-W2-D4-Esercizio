package danrusso.U5_W2_D3_Esercizio.payloads;

import java.util.UUID;

public class NewBlogPostPayload {

    private String category;
    private String title;
    private String content;
    private int readingTime;
    private UUID authorId;

    public NewBlogPostPayload(String category, String title, String content, int readingTime, UUID authorId) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.authorId = authorId;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(int readingTime) {
        this.readingTime = readingTime;
    }

    @Override
    public String toString() {
        return "NewBlogPostPayload{" +
                "category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", readingTime=" + readingTime +
                '}';
    }
}
