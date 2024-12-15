package fashionline.com.api.Models.Entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "productos")
public class Product implements Serializable {
    @Id
    @Field("_id")
    private ObjectId id;

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    @Field("name")
    private String name;

    @NotBlank(message = "La descripción del producto no puede estar vacía.")
    @Field("description")
    private String description;

    @NotNull(message = "El precio no puede ser nulo.")
    @Positive(message = "El precio debe ser positivo.")
    @Field("price")
    private Double price;

    @Min(value = 0, message = "El stock no puede ser negativo.")
    @Field("stock")
    private int stock;

    @Size(min = 3, max = 20, message = "La categoría debe tener entre 3 y 20 caracteres.")
    @Field("category")
    private Category category;

    @NotBlank(message = "El tamaño del producto no puede estar vacío.")
    @Field("size")
    private String size;
}
