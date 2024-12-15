package fashionline.com.api.Models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    @Schema(description = "ID del Producto", hidden = true)
    private String id = null;

    @Schema(description = "Nombre del producto", example = "Camiseta de Algodón")
    private String name;

    @Schema(description = "Descripción del producto", example = "Camiseta de algodón 100% en varios colores.")
    private String description;

    @Schema(description = "Precio del producto", example = "19.99", type = "string")
    private String price;

    @Schema(description = "Cantidad en stock", example = "100", type = "string")
    private String stock;

    @Schema(description = "Categoría del producto", example = "CLOTHING")
    private String category;

    @Schema(description = "Tamaño del producto", example = "L")
    private String size;
}
