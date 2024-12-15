package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Mappers.MProduct;
import fashionline.com.api.Models.DAO.Repository.RProduct;
import fashionline.com.api.Models.DTO.ProductDTO;
import fashionline.com.api.Models.Entity.Product;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que implementa la interfaz {@link SProductI} para gestionar productos en la base de datos.
 * Contiene la lógica para recuperar, crear, actualizar y eliminar productos.
 */
@Data
@Service
public class SProduct implements SProductI {
    private final RProduct repositoryProduct;

    @Autowired
    private SProduct(RProduct repositoryProduct) {
        this.repositoryProduct = repositoryProduct;
    }

    /**
     * Obtiene todos los productos de la base de datos.
     *
     * @return Una lista de todos los productos.
     * @throws UnsupportedOperationException Si no se encuentran productos en la base de datos.
     */
    @Override
    public List<Product> getAllProducts() {
        try {
            List<Product> products = repositoryProduct.findAll();
            if (products.isEmpty()) {
                throw new UnsupportedOperationException("No se encontró ningún producto");
            }
            return products;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar productos en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id El identificador único del producto.
     * @return El producto correspondiente al ID.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    @Override
    public Product getProductById(String id) {
        try {
            return repositoryProduct.findById(new ObjectId(id)).orElseThrow(() -> new Exception("No se encontró el producto"));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar el producto con el id " + id + " en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Busca productos por su nombre.
     *
     * @param name El nombre del producto.
     * @return Una lista de productos que coinciden con el nombre.
     * @throws UnsupportedOperationException Si no se encuentran productos con el nombre proporcionado.
     */
    @Override
    public List<Product> getProductsByName(String name) {
        try {
            return repositoryProduct.findByName(name);
        } catch (Exception e) {
            throw new UnsupportedOperationException("No se encontró ningún producto con el nombre " + name + " en la db");
        }
    }

    /**
     * Busca productos por su categoría.
     *
     * @param category La categoría de los productos.
     * @return Una lista de productos que pertenecen a la categoría especificada.
     * @throws UnsupportedOperationException Si no se encuentran productos en la categoría proporcionada.
     */
    @Override
    public List<Product> getProductsByCategory(String category) {
        try {
            List<Product> products = repositoryProduct.findByCategory(category);
            if (products.isEmpty()) {
                throw new Exception("No se encontró ningún producto");
            }
            return products;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar productos con la categoría " + category + " en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Crea un nuevo producto en la base de datos.
     *
     * @param productDTO Los datos del producto a crear.
     * @return El producto recién creado.
     * @throws UnsupportedOperationException Si no se puede crear el producto.
     */
    @Override
    public Product createProduct(ProductDTO productDTO) {
        try {
            return Optional.of(productDTO)
                    .map(MProduct::toEntity)
                    .map(repositoryProduct::save)
                    .orElseThrow(() -> new UnsupportedOperationException("El producto no se pudo crear."));
        } catch (Exception e) {
            throw new UnsupportedOperationException("El producto no se pudo crear: " + e.getMessage());
        }
    }

    /**
     * Actualiza los detalles de un producto existente.
     *
     * @param id         El identificador del producto a actualizar.
     * @param productDTO Los nuevos datos del producto.
     * @return El producto actualizado.
     * @throws UnsupportedOperationException Si no se puede actualizar el producto con el ID proporcionado.
     */
    @Override
    public Product updateProduct(String id, ProductDTO productDTO) {
        try {
            return Optional.of(productDTO)
                    .map(MProduct::toEntity)
                    .map(repositoryProduct::save)
                    .orElseThrow(() -> new Exception("El producto con ID " + id + " no se pudo actualizar."));
        } catch (Exception e) {
            throw new UnsupportedOperationException("El producto con ID " + id + " no se pudo actualizar. [" + e.getMessage() + "]");
        }
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param id El identificador del producto a eliminar.
     * @return Un mensaje indicando si la eliminación fue exitosa.
     * @throws UnsupportedOperationException Si no se puede eliminar el producto con el ID proporcionado.
     */
    @Override
    public String deleteProduct(String id) {
        try {
            if (!repositoryProduct.existsById(new ObjectId(id))) {
                throw new Exception("El producto no fue encontrado");
            }
            repositoryProduct.deleteById(new ObjectId(id));
            return "El producto con ID '" + id + "' fue eliminado.";
        } catch (Exception e) {
            throw new UnsupportedOperationException("El producto con ID '" + id + "' no se pudo eliminar [" + e.getMessage() + "]");
        }
    }
}
