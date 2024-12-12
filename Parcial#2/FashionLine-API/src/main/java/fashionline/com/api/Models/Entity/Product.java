package fashionline.com.api.Models.Entity;

import jakarta.validation.constraints.*;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productos")
public class Product implements Serializable {
    @Id
    private ObjectId id;

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    private String name;

    @NotBlank(message = "La descripción del producto no puede estar vacía.")
    private String description;

    @NotNull(message = "El precio no puede ser nulo.")
    @Positive(message = "El precio debe ser positivo.")
    private Double price;

    @Min(value = 0, message = "El stock no puede ser negativo.")
    private int stock;

    @Size(min = 3, max = 20, message = "La categoría debe tener entre 3 y 20 caracteres.")
    private String category;

    @NotBlank(message = "El tamaño del producto no puede estar vacío.")
    private String size;
}
