package ZhandosSpring.models;


import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "FullName should not be empty")
    @Size(min = 2, max = 100, message = "FullName should be between 2 and 100 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "FullName should be in this format : Name, Surname")
    private String fullName;
    @Min(value = 1900, message = "BirthYear should be greater than 1900")
    @Max(value = 2024, message = "BirthYear should be less than 2024")
    private int birthYear;
    public Person(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }
    public Person() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
