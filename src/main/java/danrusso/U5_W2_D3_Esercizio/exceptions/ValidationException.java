package danrusso.U5_W2_D3_Esercizio.exceptions;

import java.util.List;

public class ValidationException extends RuntimeException {

    private List<String> errorMessages;

    public ValidationException(List<String> errors) {

        super("Validation errors occured");
        this.errorMessages = errors;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
