package fashionline.com.api.Models.DAO.Repository;

import fashionline.com.api.Models.Entity.Product;
import fashionline.com.api.Models.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProduct extends MongoRepository<Product, ObjectId> {

}
