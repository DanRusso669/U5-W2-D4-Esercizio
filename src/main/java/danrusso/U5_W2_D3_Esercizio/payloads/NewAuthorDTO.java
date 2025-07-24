package danrusso.U5_W2_D3_Esercizio.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewAuthorDTO(
        @NotBlank(message = "Name is mandatory.")
        @Size(min = 3, max = 15, message = "Name's lenght must be between 3 and 15.")
        String name,
        @NotBlank(message = "Surname is mandatory.")
        @Size(min = 3, max = 15, message = "Name's lenght must be between 3 and 15.")
        String surname,
        @NotEmpty(message = "Email is mandatory.")
        @Email
        String email,
        @NotEmpty(message = "Date of Birth is mandatory.")
        String DOB
) {
}
