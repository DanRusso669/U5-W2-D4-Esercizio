package danrusso.U5_W2_D3_Esercizio.entities;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "blog posts")
public class BlogPost {
    @ManyToOne
    Author author;
    @Id
    @GeneratedValue
    private UUID id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;

    public BlogPost() {
    }

    public BlogPost(String category, String title, String content, int readingTime, Author author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.author = author;
    }

    public UUID getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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
        return "BlogPost{" +
                "author=" + author +
                ", id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", content='" + content + '\'' +
                ", readingTime=" + readingTime +
                '}';
    }
}
