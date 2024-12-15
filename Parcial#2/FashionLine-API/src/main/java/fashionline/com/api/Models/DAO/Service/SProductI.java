package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Models.DTO.ProductDTO;

import java.util.List;

/**
 * Interfaz que define los métodos para acceder y gestionar productos en la base de datos.
 * Implementada por el servicio {@link SProduct}.
 */
public interface SProductI {

    /**
     * Obtiene todos los productos de la base de datos.
     * *
     * Se realiza una validación para verificar que la lista de productos no esté vacía.
     * Si la lista está vacía, se lanzará una excepción {@link UnsupportedOperationException}.
     *
     * @return Una lista de todos los productos mapeados a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentra ningún producto.
     */
    List<ProductDTO> getAllProducts();

    /**
     * Busca un producto por su ID.
     * *
     * Este método buscará el producto correspondiente al ID proporcionado.
     * Si el producto no existe, se lanzará una excepción {@link UnsupportedOperationException}.
     *
     * @param id El identificador único del producto.
     * @return El producto correspondiente al ID mapeado a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    ProductDTO getProductById(String id);

    /**
     * Busca productos por su nombre.
     * *
     * Este método devolverá una lista de productos que coinciden con el nombre proporcionado.
     * Si no se encuentra ningún producto con ese nombre, se lanzará una excepción {@link UnsupportedOperationException}.
     *
     * @param name El nombre del producto.
     * @return Una lista de productos que coinciden con el nombre, mapeada a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentran productos con ese nombre.
     */
    List<ProductDTO> getProductsByName(String name);

    /**
     * Busca productos por su categoría.
     * *
     * Este método devolverá una lista de productos que pertenecen a la categoría proporcionada.
     * Si no se encuentran productos en esa categoría, se lanzará una excepción {@link UnsupportedOperationException}.
     *
     * @param category La categoría de los productos.
     * @return Una lista de productos en la categoría especificada, mapeada a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentran productos en esa categoría.
     */
    List<ProductDTO> getProductsByCategory(String category);


    /**
     * Busca productos por su categoría.
     * *
     * Este método devolverá una lista de productos que pertenecen a la categoría proporcionada.
     * Si no se encuentran productos en esa categoría, se lanzará una excepción {@link UnsupportedOperationException}.
     *
     * @param size La categoría de los productos.
     * @return Una lista de productos en la categoría especificada, mapeada a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentran productos en esa categoría.
     */
    List<ProductDTO> getProductsBySize(String size);

    /**
     * Crea un nuevo producto en la base de datos.
     * *
     * Este método recibe los datos del producto a crear, los guarda en la base de datos y devuelve
     * el producto creado mapeado a {@link ProductDTO}.
     *
     * @param productDTO Los datos del producto a crear.
     * @return El producto recién creado mapeado a {@link ProductDTO}.
     */
    ProductDTO createProduct(ProductDTO productDTO);

    /**
     * Actualiza los detalles de un producto existente.
     * *
     * Este método actualizará el producto con el ID proporcionado con los nuevos datos recibidos.
     * Si no se encuentra el producto, se lanzará una excepción {@link UnsupportedOperationException}.
     *
     * @param id         El identificador del producto a actualizar.
     * @param productDTO Los nuevos datos del producto.
     * @return El producto actualizado mapeado a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    ProductDTO updateProduct(String id, ProductDTO productDTO);

    /**
     * Elimina un producto de la base de datos.
     * *
     * Este método eliminará el producto con el ID proporcionado.
     * Si no se encuentra el producto, se lanzará una excepción {@link UnsupportedOperationException}.
     *
     * @param id El identificador del producto a eliminar.
     * @return Un mensaje indicando el estado de la eliminación.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    String deleteProduct(String id);
}