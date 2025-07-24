package danrusso.U5_W2_D3_Esercizio.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id, String type) {
        super(
                type + " with id " + id + " not found."
        );
    }
}
