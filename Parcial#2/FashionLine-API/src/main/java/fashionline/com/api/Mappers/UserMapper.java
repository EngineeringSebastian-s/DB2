package fashionline.com.api.Mappers;

import fashionline.com.api.Models.DTO.UserDTO;
import fashionline.com.api.Models.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * Clase que mapea entre entidades User y objetos DTO UserDTO.
 * Esta clase es responsable de convertir los datos entre la capa de persistencia y la capa de presentación.
 */
@Component
public class UserMapper {
    /**
     * Convierte un UserDTO en una entidad User.
     *
     * @param userDTO El DTO del usuario que se va a convertir en entidad.
     * @return La entidad User correspondiente.
     */
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(new ObjectId(userDTO.getId()));
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    /**
     * Convierte una entidad User en un UserDTO.
     *
     * @param user La entidad User que se va a convertir en DTO.
     * @return El DTO del usuario correspondiente.
     */
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDTO.getId());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
