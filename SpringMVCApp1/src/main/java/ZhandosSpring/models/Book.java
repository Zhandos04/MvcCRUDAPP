package ZhandosSpring.models;

import javax.validation.constraints.*;

public class Book {
    private int id;

    @NotEmpty(message = "Book name should not be empty!")
    @Size(min = 2, max = 30, message = "Book name length should be between 2 and 30 characters!")
    private String name;
    @NotEmpty(message = "Author name should not be empty!")
    private String author;
    @Max(value = 2024, message = "Year can not be a more than 2024")
    @Min(value = 1900, message = "Year can not be a less than 1900")
    private int year;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
