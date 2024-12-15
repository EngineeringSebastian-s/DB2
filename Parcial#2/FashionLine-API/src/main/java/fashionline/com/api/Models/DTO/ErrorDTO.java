package fashionline.com.api.Models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    @Schema(description = "Código de error", example = "404", type = "integer")
    public int code;

    @Schema(description = "Mensaje de error", example = "Error al obtener el elemento desde la DB")
    public String message;
}
