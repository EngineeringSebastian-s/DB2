package fashionline.com.api.Models.DAO.Service;

import fashionline.com.api.Exception.ApiException;
import fashionline.com.api.Exception.ApiResponse;
import fashionline.com.api.Models.DAO.Repository.RProduct;
import fashionline.com.api.Models.DAO.Repository.RUser;
import fashionline.com.api.Models.Entity.Product;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Builder
@Service
public class SProduct {
    private final RProduct repositoryProduct;

    @Autowired
    public SProduct(RProduct repositoryProduct) {
        this.repositoryProduct = repositoryProduct;
    }

    public List<Product> getAllProducts() {
        List<Product> products = repositoryProduct.findAll();
        if (products.isEmpty()) {
            throw new ApiException(new ApiResponse(
                    "No se encontró ningún producto en la db",
                    HttpStatus.NOT_FOUND.value()
            ));
        }
        return products;
    }
}
