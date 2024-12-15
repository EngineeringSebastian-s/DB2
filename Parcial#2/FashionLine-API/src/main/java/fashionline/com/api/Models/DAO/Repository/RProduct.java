package fashionline.com.api.Models.DAO.Repository;

import fashionline.com.api.Models.Entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RProduct extends MongoRepository<Product, ObjectId> {
    /**
     * Busca un producto por su nombre.
     *
     * @param name El nombre del producto.
     * @return El producto correspondiente al nombre.
     */
    @Query("{ 'name' : ?0 }")
    List<Product> findByName(String name);

    /**
     * Busca productos por su categoría.
     *
     * @param category La categoría de los productos.
     * @return Una lista de productos que pertenecen a la categoría.
     */
    @Query("{ 'category' : ?0 }")
    List<Product> findByCategory(String category);
}
