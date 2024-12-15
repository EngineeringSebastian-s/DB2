package fashionline.com.api.Controllers;

import fashionline.com.api.Models.DAO.Service.SUserI;
import fashionline.com.api.Models.DTO.ErrorDTO;
import fashionline.com.api.Models.DTO.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {
    private final SUserI serviceUserI;

    @Autowired
    public UserController(SUserI serviceUserI) {
        this.serviceUserI = serviceUserI;
    }

    /**
     * Busca un usuario utilizando su ID.
     * *
     * Recuperar un usuario de la base de datos proporcionando su identificador único.
     * Si el usuario con el ID proporcionado existe, se devolverá un objeto Usuario (representado como un DTO).
     * En caso de que el usuario no sea encontrado, se devolverá un mensaje de error con el código HTTP 404.
     *
     * @param id El identificador único del usuario que se desea recuperar. Este parámetro debe ser el ID del usuario.
     * @return Un objeto `ResponseEntity` que contiene:
     * - El usuario correspondiente al ID si se encuentra (código HTTP 200).
     * - Un mensaje de error si no se encuentra el usuario (código HTTP 404).
     * *
     * Respuestas posibles:
     * - **200 OK**: El usuario se encuentra, se retorna un objeto `UserDTO` con la información del usuario.
     * - **404 Not Found**: No se encuentra ningún usuario con el ID proporcionado, se retorna un objeto `ErrorResponse` con el mensaje de error.
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Recupera un usuario utilizando su ID.",
            responses = {
                    @ApiResponse(description = "Usuario encontrado",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Usuario no encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    ),

            })
    public ResponseEntity<?> getUserById(@PathVariable @Parameter(description = "ID del usuario") String id) {
        try {
            return new ResponseEntity<>(serviceUserI.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Recupera un usuario desde la base de datos utilizando su dirección de correo electrónico.
     * Este endpoint devuelve un usuario cuando se proporciona un correo electrónico válido.
     * Si el usuario con el correo electrónico proporcionado existe, se devuelve con un código de estado 200 (OK).
     * Si no se encuentra el usuario, se devuelve un mensaje de error con un código de estado 404 (No encontrado).
     *
     * @param email La dirección de correo electrónico del usuario que se busca.
     * @return Una respuesta con el estado de la operación:
     * - **200 OK**: Se retorna el usuario en formato `UserDTO`.
     * - **404 Not Found**: No se encuentra el usuario, se retorna un mensaje de error en formato `ErrorResponse`.
     * *
     * Respuestas posibles:
     * - **200 OK**: El usuario con el correo electrónico proporcionado es encontrado y se retorna un objeto `UserDTO`.
     * - **404 Not Found**: El usuario no es encontrado y se retorna un objeto `ErrorResponse` con el mensaje de error.
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar usuario por correo electrónico", description = "Recupera un usuario utilizando su correo electrónico.",
            responses = {
                    @ApiResponse(description = "Usuario encontrado",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Usuario no encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    ),
            })
    public ResponseEntity<?> getUsersByEmail(@PathVariable @Parameter(description = "Correo electrónico del usuario") String email) {
        try {
            return new ResponseEntity<>(serviceUserI.getUserByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
