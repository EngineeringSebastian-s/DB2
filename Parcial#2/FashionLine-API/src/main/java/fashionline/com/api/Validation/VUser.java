package fashionline.com.api.Validation;

import org.bson.types.ObjectId;

import java.util.regex.Pattern;

/**
 * Validación para usuarios, encargada de realizar las validaciones necesarias
 * en los campos del usuario (como el correo electrónico y el ID).
 */
public class VUser {

    /**
     * Patrón para validar correos electrónicos:
     * - Asegura que haya un texto antes y después del símbolo @.
     * - Requiere un dominio válido con un punto (.) después del símbolo @.
     * - No permite espacios en ninguna parte del correo.
     */
    private static final String EMAIL_PATTERN = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

    /**
     * Válida si el ID proporcionado es un ObjectId válido de MongoDB.
     *
     * @param id El identificador a validar.
     * @throws UnsupportedOperationException Si el ID no es válido.
     */
    public static void validateId(String id) {
        if (!ObjectId.isValid(id)) {
            throw new UnsupportedOperationException("El ID '" + id + "' no es válido. Asegúrate de que tiene 24 caracteres y solo incluye dígitos hexadecimales (0-9, a-f, A-F).");
        }
    }

    /**
     * Válida que el correo electrónico cumpla con el patrón especificado.
     *
     * @param email El correo electrónico a validar.
     * @throws UnsupportedOperationException Si el correo no es válido.
     */
    public static void validateEmail(String email) {
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            throw new UnsupportedOperationException("El correo electrónico '" + email + "' no es válido. Asegúrate de que sigue el formato correcto.");
        }
    }
}

