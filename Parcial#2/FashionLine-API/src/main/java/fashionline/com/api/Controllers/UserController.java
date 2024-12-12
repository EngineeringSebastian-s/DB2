package fashionline.com.api.Controllers;

import fashionline.com.api.Models.DAO.Service.SUser;
import fashionline.com.api.Models.Entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {
    private final SUser serviceUser;

    @Autowired
    public UserController(SUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    /**
     * Encuentra un usuario por su ID.
     *
     * @param id El ID del usuario a recuperar.
     * @return El objeto Usuario correspondiente al ID proporcionado.
     */
    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Recupera un usuario utilizando su ID.")
    public User getUserById(@PathVariable @Parameter(description = "ID del usuario") String id) {
        return serviceUser.getUserById(id);
    }

    /**
     * Encuentra usuarios por su dirección de correo electrónico.
     *
     * @param email La dirección de correo electrónico por la que filtrar los usuarios.
     * @return Un usuario que coincide con el correo electrónico proporcionado.
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar usuario por correo electrónico", description = "Recupera un usuario utilizando su correo electrónico.")
    public User getUsersByEmail(@PathVariable @Parameter(description = "Correo electrónico del usuario") String email) {
        return serviceUser.getUserByEmail(email);
    }
}
