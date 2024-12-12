package fashionline.com.api.Models.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Document(collection = "usuarios")
public class User implements Serializable {
    @Id
    @Field("_id")
    private ObjectId id;

    @NotEmpty(message = "El email no puede estar vacío")
    @Size(min = 14, max = 254, message = "El email debe tener entre 14 y 254 caracteres")
    @Email(message = "El email debe ser válido")
    @Indexed(unique = true)
    @Field("email")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, " +
                    "una letra mayúscula, una letra minúscula, " +
                    "un número y un carácter especial.")
    @Field("password")
    private String password;
}