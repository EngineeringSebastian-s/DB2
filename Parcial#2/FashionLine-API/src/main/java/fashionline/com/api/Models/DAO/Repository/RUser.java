package fashionline.com.api.Models.DAO.Repository;

import fashionline.com.api.Models.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RUser extends MongoRepository<User, ObjectId> {
    @Query("{ 'email' : ?0 }")
    List<User> findByEmail(String email);
}
