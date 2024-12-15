package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Mappers.MProduct;
import fashionline.com.api.Models.DAO.Repository.RProduct;
import fashionline.com.api.Models.DTO.ProductDTO;
import fashionline.com.api.Models.Entity.Product;
import fashionline.com.api.Validation.VProduct;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * *
     * Este método verifica si existen productos en la base de datos. Si la lista está vacía,
     * lanza una excepción {@link UnsupportedOperationException}.
     * Luego, mapea los productos a {@link ProductDTO} utilizando un flujo de datos.
     *
     * @return Una lista de todos los productos mapeados a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentran productos en la base de datos.
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        try {
            List<Product> products = repositoryProduct.findAll();
            if (products.isEmpty()) {
                throw new Exception("No se encontró ningún producto");
            }
            return products.stream()
                    .map(MProduct::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar productos en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Busca un producto por su ID.
     * *
     * Este método busca el producto correspondiente al ID proporcionado. Si no existe,
     * lanza una excepción {@link UnsupportedOperationException}.
     * Luego, mapea el producto encontrado a un {@link ProductDTO}.
     *
     * @param id El identificador único del producto.
     * @return El producto correspondiente al ID mapeado a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentra el producto con el ID proporcionado.
     */
    @Override
    public ProductDTO getProductById(String id) {
        try {
            return repositoryProduct.findById(new ObjectId(id))
                    .map(MProduct::toDTO)
                    .orElseThrow(() -> new Exception("No se encontró el producto"));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar el producto con el id " + id + " en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Busca productos por su nombre.
     * *
     * Este método devuelve una lista de productos que coinciden con el nombre proporcionado.
     * Si no se encuentran productos con ese nombre, se lanza una excepción {@link UnsupportedOperationException}.
     * Luego, mapea los productos encontrados a {@link ProductDTO}.
     *
     * @param name El nombre del producto.
     * @return Una lista de productos que coinciden con el nombre, mapeada a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentran productos con el nombre proporcionado.
     */
    @Override
    public List<ProductDTO> getProductsByName(String name) {
        try {
            List<Product> products = repositoryProduct.findByName(name);
            if (products.isEmpty()) {
                throw new Exception("No se encontro ningun producto");
            }
            return products.stream()
                    .map(MProduct::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar productos con el nombre '" + name + "' en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Busca productos por su categoría.
     * *
     * Este método devuelve una lista de productos que pertenecen a la categoría proporcionada.
     * Si no se encuentran productos en esa categoría, se lanza una excepción {@link UnsupportedOperationException}.
     * Luego, mapea los productos encontrados a {@link ProductDTO}.
     *
     * @param category La categoría de los productos.
     * @return Una lista de productos en la categoría especificada, mapeada a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentran productos en esa categoría.
     */
    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        try {
            VProduct.validateCategory(category);
            List<Product> products = repositoryProduct.findByCategory(category);
            if (products.isEmpty()) {
                throw new Exception("No se encontro ningun producto");
            }

            return products.stream()
                    .map(MProduct::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar productos con la categoría " + category + " en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Busca productos por su categoría.
     * *
     * Este método devuelve una lista de productos que pertenecen a la categoría proporcionada.
     * Si no se encuentran productos en esa categoría, se lanza una excepción {@link UnsupportedOperationException}.
     * Luego, mapea los productos encontrados a {@link ProductDTO}.
     *
     * @param size La categoría de los productos.
     * @return Una lista de productos en la categoría especificada, mapeada a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se encuentran productos en esa categoría.
     */
    @Override
    public List<ProductDTO> getProductsBySize(String size) {
        try {
            VProduct.validateSize(size);
            List<Product> products = repositoryProduct.findBySize(size);
            if (products.isEmpty()) {
                throw new Exception("No se encontro ningun producto");
            }

            return products.stream()
                    .map(MProduct::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al buscar productos con la categoría " + size + " en la db [" + e.getMessage() + "]");
        }
    }

    /**
     * Crea un nuevo producto en la base de datos.
     * *
     * Este método recibe los datos del producto a crear, los guarda en la base de datos y luego mapea
     * el producto creado a un {@link ProductDTO}.
     *
     * @param productDTO Los datos del producto a crear.
     * @return El producto recién creado mapeado a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se puede crear el producto.
     */
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        try {
            return Optional.of(productDTO)
                    .map(MProduct::toEntity)
                    .map(repositoryProduct::save)
                    .map(MProduct::toDTO)
                    .orElseThrow(() -> new UnsupportedOperationException("El producto no se pudo crear."));
        } catch (Exception e) {
            throw new UnsupportedOperationException("El producto no se pudo crear: " + e.getMessage());
        }
    }

    /**
     * Actualiza los detalles de un producto existente.
     * *
     * Este método actualiza el producto con el ID proporcionado utilizando los nuevos datos recibidos.
     * Si no se puede actualizar el producto, lanza una excepción {@link UnsupportedOperationException}.
     * Luego, mapea el producto actualizado a {@link ProductDTO}.
     *
     * @param id         El identificador del producto a actualizar.
     * @param productDTO Los nuevos datos del producto.
     * @return El producto actualizado mapeado a {@link ProductDTO}.
     * @throws UnsupportedOperationException Si no se puede actualizar el producto con el ID proporcionado.
     */
    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        try {
            return Optional.of(productDTO)
                    .map(MProduct::toEntity)
                    .map(repositoryProduct::save)
                    .map(MProduct::toDTO)
                    .orElseThrow(() -> new Exception("El producto con ID " + id + " no se pudo actualizar."));
        } catch (Exception e) {
            throw new UnsupportedOperationException("El producto con ID " + id + " no se pudo actualizar. [" + e.getMessage() + "]");
        }
    }

    /**
     * Elimina un producto de la base de datos.
     * *
     * Este método elimina el producto con el ID proporcionado. Si no se encuentra el producto,
     * se lanzará una excepción {@link UnsupportedOperationException}.
     * Si el producto se elimina correctamente, devuelve un mensaje indicando la eliminación exitosa.
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
