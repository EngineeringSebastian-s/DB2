package fashionline.com.api.Controllers;

import fashionline.com.api.Mappers.MUser;
import fashionline.com.api.Models.DAO.Service.SUser;
import fashionline.com.api.Models.DTO.ErrorDTO;
import fashionline.com.api.Models.DTO.UserDTO;
import fashionline.com.api.Security.jwt.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autentificación", description = "Operaciones relacionadas con autentificación de usuarios")
public class AuthController {

    private final SUser serviceUser;
    private final JwtService jwtService;

    @Autowired
    public AuthController(final SUser serviceUser, final JwtService jwtService) {
        this.serviceUser = serviceUser;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Iniciar sesión de usuario",
            description = "Permite a un usuario iniciar sesión proporcionando su correo electrónico y contraseña. Retorna un token JWT si las credenciales son válidas.",
            responses = {
                    @ApiResponse(
                            description = "Inicio de sesión exitoso",
                            responseCode = "200",
                            content = @Content(mediaType = "application/plain", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            description = "Credenciales inválidas",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    ),
                    @ApiResponse(
                            description = "Error en la solicitud",
                            responseCode = "400",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> login(@RequestBody UserDTO reqUser) {
        try {
            UserDTO user = serviceUser.getUserByEmail(reqUser.getEmail());
            if (user == null) {
                return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Invalid Credentials"), HttpStatus.UNAUTHORIZED);
            }

            boolean passwordMatch = new BCryptPasswordEncoder().matches(reqUser.getPassword(), user.getPassword());
            if (!passwordMatch) {
                return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Invalid Credentials"), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(jwtService.generateToken(MUser.toEntity(user)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify")
    @Operation(
            summary = "Verificar token JWT",
            description = "Verifica si el token JWT proporcionado es válido. Si es válido, retorna la información del usuario sin la contraseña.",
            responses = {
                    @ApiResponse(
                            description = "Token válido y usuario encontrado",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))
                    ),
                    @ApiResponse(
                            description = "Token inválido",
                            responseCode = "418",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    public ResponseEntity<?> verify(@RequestHeader("Authorization") String bearerToken) {

        String token = bearerToken.split(" ")[1];
        String email = jwtService.extractEmail(token);
        UserDetails user = serviceUser.loadUserByUsername(email);

        if (jwtService.validateToken(token, user)) {
            UserDTO finalUser = serviceUser.getUserByEmail(email);
            finalUser.setPassword("");
            return new ResponseEntity<>(finalUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorDTO(HttpStatus.I_AM_A_TEAPOT.value(), "Token invalido."), HttpStatus.I_AM_A_TEAPOT);
        }
    }
}