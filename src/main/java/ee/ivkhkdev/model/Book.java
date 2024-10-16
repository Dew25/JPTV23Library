package ee.ivkhkdev.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private Author[] authors = new Author[10];
    private int publishedYear;

    public Book() {
        this.id = UUID.randomUUID();
    }

    public Book(String title, Author[] authors, int publishedYear) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.authors = authors;
        this.publishedYear = publishedYear;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return publishedYear == book.publishedYear && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Arrays.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Arrays.hashCode(authors);
        result = 31 * result + publishedYear;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", authors=").append(Arrays.toString(authors));
        sb.append(", publishedYear=").append(publishedYear);
        sb.append('}');
        return sb.toString();
    }
}