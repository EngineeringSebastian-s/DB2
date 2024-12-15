package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Models.DTO.UserDTO;

/**
 * Interfaz que define los métodos necesarios para interactuar con los usuarios
 * dentro del sistema. Esta interfaz debe ser implementada por el servicio `SUser`
 * para proporcionar la funcionalidad de obtener usuarios por su ID o correo electrónico.
 */
public interface SUserI {

    /**
     * Obtiene un usuario de la base de datos por su identificador único (ID).
     *
     * @param id El identificador único del usuario (en formato String).
     * @return El usuario asociado con el ID proporcionado.
     * @throws UnsupportedOperationException Si el ID no es válido o no se encuentra un usuario con ese ID.
     */
    UserDTO getUserById(String id);

    /**
     * Obtiene un usuario de la base de datos por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El usuario asociado con el correo electrónico proporcionado.
     * @throws UnsupportedOperationException Si el correo electrónico no es válido o no se encuentra un usuario
     *                                       con ese correo.
     */
    UserDTO getUserByEmail(String email);
}
