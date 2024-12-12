package fashionline.com.api.Models.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private String price;
    private String stock;
    private String category;
    private String size;
}
