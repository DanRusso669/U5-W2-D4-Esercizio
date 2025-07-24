package danrusso.U5_W2_D3_Esercizio.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    @Column(name = "date of birth")
    private String DOB;
    private String avatar;

    public Author() {
    }

    public Author(String name, String surname, String email, String DOB) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.DOB = DOB;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", DOB='" + DOB + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
