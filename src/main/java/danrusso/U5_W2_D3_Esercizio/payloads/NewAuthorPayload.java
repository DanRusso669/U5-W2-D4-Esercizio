package danrusso.U5_W2_D3_Esercizio.payloads;

public class NewAuthorPayload {
    private String name;
    private String surname;
    private String email;
    private String DOB;


    public NewAuthorPayload(String name, String surname, String email, String DOB) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.DOB = DOB;
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

    @Override
    public String toString() {
        return "NewAuthorPayload{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", DOB='" + DOB + '\'' +
                '}';
    }
}

