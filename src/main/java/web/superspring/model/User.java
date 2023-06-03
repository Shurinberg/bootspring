package web.superspring.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "^[a-zA-Z-А-аЯ-я]+$", message = "Только русские и английские буквы")
    @NotEmpty(message = "Not null")
    private String name;
    @NotEmpty(message = "Not null")
    @Size(min = 2, max = 15, message = "Необходимое количество знаков от2 до 15")
    private String lastName;
    @Max(value = 100, message = "Положительное число от 0 до 100")
    @Min(value = 0, message = "Положительное число от 0 до 100")
    private int age;

    public User() {

    }

    public User(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
