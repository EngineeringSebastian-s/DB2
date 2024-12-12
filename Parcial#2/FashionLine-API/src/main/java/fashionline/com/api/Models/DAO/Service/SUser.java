package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Exception.ApiException;
import fashionline.com.api.Exception.ApiResponse;
import fashionline.com.api.Models.DAO.Repository.RUser;
import fashionline.com.api.Models.Entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Data
@Builder
@Service
public class SUser implements UserDetailsService {
    private final RUser repositoryUser;

    @Autowired
    public SUser(RUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    /**
     * Patrón para validar correos electrónicos:
     * - Asegura que haya un texto antes y después del símbolo @.
     * - Requiere un dominio válido con un punto (.) después del símbolo @.
     * - No permite espacios en ninguna parte del correo.
     */
    public static final String EMAIL_PATTERN = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

    /**
     * Válida si el ID proporcionado es un ObjectId válido de MongoDB.
     *
     * @param id El identificador a validar.
     * @throws ApiException Si el ID no es válido.
     */
    private void ValidationId(String id) {
        if (!ObjectId.isValid(id)) {
            throw new ApiException(new ApiResponse(
                    "El id '" + id + "' no es válido. Asegúrate de que tiene 24 caracteres y solo incluye dígitos hexadecimales (0-9, a-f, A-F).",
                    HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    /**
     * Válida que el correo electrónico cumpla con el patrón especificado.
     *
     * @param email El correo electrónico a validar.
     * @throws ApiException Si el correo no es válido.
     */
    private void ValidationEmail(String email) {
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            throw new ApiException(new ApiResponse(
                    "El correo electrónico '" + email + "' no es válido. Asegúrate de que sigue el formato correcto.",
                    HttpStatus.BAD_REQUEST.value()
            ));
        }
    }

    /**
     * Obtiene un usuario por su ID. Si el ID es válido, se busca el usuario en la base de datos.
     *
     * @param id El identificador del usuario a buscar.
     * @return El usuario encontrado.
     * @throws ApiException Si no se encuentra el usuario con el ID proporcionado.
     */
    public User getUserById(String id) {
        ValidationId(id);

        return repositoryUser.findById(new ObjectId(id))
                .orElseThrow(() -> new ApiException(
                        new ApiResponse("El usuario con id '" + id + "' no fue encontrado.",
                                HttpStatus.NOT_FOUND.value())
                ));
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El usuario encontrado.
     * @throws ApiException Si no se encuentra un usuario con el correo electrónico proporcionado.
     */
    public User getUserByEmail(String email) {
        ValidationEmail(email);

        List<User> users = repositoryUser.findByEmail(email);
        if (users == null || users.isEmpty()) {
            throw new ApiException(new ApiResponse(
                    "No se encontró ningún usuario con el correo electrónico: '" + email + "'.",
                    HttpStatus.NOT_FOUND.value()
            ));
        }

        return users.getFirst();
    }

    /**
     * Carga un usuario basado en su nombre de usuario (en este caso, el correo electrónico).
     * Este método es parte de la implementación de `UserDetailsService` de Spring Security.
     * Busca un usuario en la base de datos utilizando el correo electrónico como nombre de usuario.
     * Si no se encuentra un usuario con el correo electrónico proporcionado, se lanza una excepción `UsernameNotFoundException`.
     *
     * @param username El nombre de usuario (correo electrónico) con el que se desea autenticar al usuario.
     * @return Un objeto `UserDetails` que contiene la información del usuario autenticado, como su correo electrónico, contraseña y roles.
     * @throws UsernameNotFoundException Si no se encuentra un usuario con el correo electrónico proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repositoryUser.findByEmail(username).getFirst();
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
