package com.etech.controller.admin;

import com.etech.model.Product;
import com.etech.model.dto.AdminProductDto;
import com.etech.repository.CategoryRepository;
import com.etech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestBody AdminProductDto product) {
        Product lastRow = productRepository.findTopByOrderByIdDesc();
        Product newProduct = new Product();
        try {
            newProduct.setTitle(product.getTitle());
            newProduct.setCategory(categoryRepository.findById(product.getCategory()).orElse(null));
            if (newProduct.getCategory() == null)
                throw new RuntimeException("Категорії з ID: " + product.getCategory() + " не існує");
            newProduct.setPrice(product.getPrice());
            newProduct.setProducer(product.getProducer());
            newProduct.setDiscount(product.getDiscount());
            newProduct.setAmount(product.getAmount());
            newProduct.setDescription(product.getDescription());
            newProduct.setImgPath((lastRow.getId() + 1) + ".png");

            productRepository.save(newProduct);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(productRepository.findTopByOrderByIdDesc());
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "Товар успішно видалено";
    }
}