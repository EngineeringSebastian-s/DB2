package fashionline.com.api.Mappers;

import fashionline.com.api.Models.DTO.ProductDTO;
import fashionline.com.api.Models.Entity.Category;
import fashionline.com.api.Models.Entity.Product;
import fashionline.com.api.Models.Entity.Size;
import org.springframework.stereotype.Component;

/**
 * Clase que mapea entre entidades Product y objetos DTO ProductDTO.
 * Esta clase es responsable de convertir los datos entre la capa de persistencia y la capa de presentación.
 */
@Component
public class MProduct {
    /**
     * Convierte un ProductDTO en una entidad Product.
     *
     * @param productDTO El DTO del producto que se va a convertir en entidad.
     * @return La entidad Product correspondiente.
     */
    public static Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(Double.valueOf(productDTO.getPrice()));
        product.setCategory(Category.valueOf(productDTO.getCategory()));
        product.setSize(Size.valueOf(productDTO.getSize()));
        return product;
    }

    /**
     * Convierte una entidad Product en un ProductDTO.
     *
     * @param product La entidad Product que se va a convertir en DTO.
     * @return El DTO del producto correspondiente.
     */
    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId().toString());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory().toString());
        productDTO.setSize(product.getSize().toString());
        return productDTO;
    }
}