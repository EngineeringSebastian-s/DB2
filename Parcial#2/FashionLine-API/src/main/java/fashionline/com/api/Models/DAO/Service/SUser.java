package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Mappers.MUser;
import fashionline.com.api.Models.DAO.Repository.RUser;
import fashionline.com.api.Models.DTO.UserDTO;
import fashionline.com.api.Models.Entity.User;
import fashionline.com.api.Validation.VUser;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Data
@Builder
@Service
public class SUser implements UserDetailsService, SUserI {
    private final RUser repositoryUser;

    @Autowired
    private SUser(RUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    /**
     * Obtiene un usuario por su ID. Si el ID es válido, se busca el usuario en la base de datos.
     *
     * @param id El identificador del usuario a buscar.
     * @return El usuario encontrado.
     * @throws UnsupportedOperationException Si no se encuentra el usuario con el ID proporcionado.
     */
    @Override
    public UserDTO getUserById(String id) {
        return repositoryUser.findById(new ObjectId(id))
                .map(user -> {
                    VUser.validateId(id);
                    return user;
                })
                .map(MUser::toDTO)
                .orElseThrow(() -> new UnsupportedOperationException("El usuario con id '" + id + "' no fue encontrado."));
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El usuario encontrado.
     * @throws UnsupportedOperationException Si no se encuentra un usuario con el correo electrónico proporcionado.
     */
    @Override
    public UserDTO getUserByEmail(String email) {
        return Optional.of(email)
                .map(validEmail -> {
                    VUser.validateEmail(validEmail);
                    return validEmail;
                })
                .map(repositoryUser::findByEmail)
                .filter(users -> !users.isEmpty())
                .map(users -> users.get(0))
                .map(MUser::toDTO)
                .orElseThrow(() -> new UnsupportedOperationException("No se encontró ningún usuario con el correo electrónico '" + email + "'."));
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
        User user = repositoryUser.findByEmail(username).get(0);
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
