package danrusso.U5_W2_D3_Esercizio.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorPayloadListDTO(String message, LocalDateTime timestamp, List<String> errorsList) {
}
