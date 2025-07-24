package danrusso.U5_W2_D3_Esercizio.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String email) {
        super("Email " + email + " already been used.");
    }
}
