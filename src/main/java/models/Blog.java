package models;

import java.time.LocalDate;

public class Blog {
    private String title;
    private String content;
    private LocalDate creationDate;
    private LocalDate lastUpdateDate;

    private Author author;

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public LocalDate getCreationDate() {return creationDate;}

    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}

    public LocalDate getLastUpdateDate() {return lastUpdateDate;}

    public void setLastUpdateDate(LocalDate lastUpdateDate) {this.lastUpdateDate = lastUpdateDate;}

    public Author getAuthor() {return author;}

    public void setAuthor(Author author) {this.author = author;}

    public Blog() {
        this.creationDate = LocalDate.now();
        this.lastUpdateDate = LocalDate.now();
    }

    public Blog(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.creationDate = LocalDate.now();
        this.lastUpdateDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
