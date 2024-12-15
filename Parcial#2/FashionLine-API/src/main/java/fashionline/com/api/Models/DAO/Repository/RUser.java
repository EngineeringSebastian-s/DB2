package fashionline.com.api.Models.DAO.Repository;

import fashionline.com.api.Models.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para acceder a los datos de los usuarios en la base de datos MongoDB.
 * Extiende {@link MongoRepository} para proporcionar operaciones CRUD básicas.
 */
@Repository
public interface RUser extends MongoRepository<User, ObjectId> {
    /**
     * Busca usuarios por su correo electrónico.
     *
     * @param email El correo electrónico del usuario.
     * @return Una lista de usuarios con el correo proporcionado.
     */
    @Query("{ 'email' : ?0 }")
    List<User> findByEmail(String email);
}
