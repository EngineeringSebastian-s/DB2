package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Models.DTO.ProductDTO;
import fashionline.com.api.Models.Entity.Product;

import java.util.List;

/**
 * Interfaz que define los métodos para acceder y gestionar productos en la base de datos.
 * Implementada por el servicio {@link SProduct}.
 */
public interface SProductI {

    /**
     * Obtiene todos los productos de la base de datos.
     *
     * @return Una lista de todos los productos.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    List<Product> getAllProducts();

    /**
     * Busca un producto por su ID.
     *
     * @param id El identificador único del producto.
     * @return El producto correspondiente al ID.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    Product getProductById(String id);

    /**
     * Busca productos por su nombre.
     *
     * @param name El nombre del producto.
     * @return Una lista de productos que coinciden con el nombre.
     * @throws UnsupportedOperationException Si no se encuentran productos con ese nombre.
     */
    List<Product> getProductsByName(String name);

    /**
     * Busca productos por su categoría.
     *
     * @param category La categoría de los productos.
     * @return Una lista de productos que pertenecen a la categoría especificada.
     * @throws UnsupportedOperationException Si no se encuentran productos en esa categoría.
     */
    List<Product> getProductsByCategory(String category);

    /**
     * Crea un nuevo producto en la base de datos.
     *
     * @param productDTO Los datos del producto a crear.
     * @return El producto recién creado.
     */
    Product createProduct(ProductDTO productDTO);

    /**
     * Actualiza los detalles de un producto existente.
     *
     * @param id         El identificador del producto a actualizar.
     * @param productDTO Los nuevos datos del producto.
     * @return El producto actualizado.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    Product updateProduct(String id, ProductDTO productDTO);

    /**
     * Elimina un producto de la base de datos.
     *
     * @param id El identificador del producto a eliminar.
     * @return Un mensaje indicando el estado de la eliminación.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    String deleteProduct(String id);
}
