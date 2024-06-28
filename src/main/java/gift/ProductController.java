package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/products/{id}")
    public ProductResponseDTO product(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new IllegalArgumentException("유효하지 않은 상품입니다");
        }

        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getUrl()
        );
    }

    @PostMapping
    public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product postProduct = new Product(
            productRequestDTO.name(),
            productRequestDTO.price(),
            productRequestDTO.url()
        );
        products.put(postProduct.getId(), postProduct);

        return new ProductResponseDTO(
            postProduct.getId(),
            postProduct.getName(),
            postProduct.getPrice(),
            postProduct.getUrl()
        );
    }

    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        if (products.containsKey(id)) {
            Product updateProduct = new Product(
                productRequestDTO.name(),
                productRequestDTO.price(),
                productRequestDTO.url()
            );
            products.put(id, updateProduct);
        }
        else {
            System.out.println("유효하지 않은 상품입니다.");
        }
        return id;
    }

    @DeleteMapping("/products/{id}")
    public Long deleteProduct(@PathVariable Long id) {
        if (products.containsKey(id)) {
            Product removedProduct = products.remove(id);
        }
        return id;
    }

    // 모든 상품 조회
    @GetMapping("/products")
    public Map<Long, Product> getAllProducts() {
        return products;
    }
}