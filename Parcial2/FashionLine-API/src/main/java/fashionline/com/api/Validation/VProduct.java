package fashionline.com.api.Validation;

import fashionline.com.api.Models.Entity.Category;
import fashionline.com.api.Models.Entity.Size;
import jakarta.validation.ValidationException;

public class VProduct {
    public static void validateCategory(String validCategory) {
        try {
            Category.valueOf(validCategory.toUpperCase());
        }catch (Exception e) {
            throw new ValidationException("La categoria '" + validCategory + "' no es valida");
        }
    }

    public static void validateSize(String validSize) {
        try {
            Size.valueOf(validSize.toUpperCase());
        }catch (Exception e) {
            throw new ValidationException("El tamaño '" + validSize + "' no es valido");
        }
    }
}
