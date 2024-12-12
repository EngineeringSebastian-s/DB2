package fashionline.com.api.Controllers;

import fashionline.com.api.Models.DAO.Service.SProduct;
import fashionline.com.api.Models.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {
    private final SProduct serviceProduct;

    @Autowired
    public ProductController(SProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return serviceProduct.getAllProducts();
    }
}
