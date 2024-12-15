package fashionline.com.api.Models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @Schema(description = "ID del usuario", hidden = true)
    private String id = null;

    @Schema(description = "Correo electrónico del usuario. Este campo es obligatorio y debe ser único.",
            example = "usuario@ejemplo.com")
    private String email;

    @Schema(description = "Contraseña del usuario. Este campo es obligatorio.",
            example = "password123")
    private String password;
}