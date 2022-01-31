package com.aize.assignment.servcies;

import com.aize.assignment.erros.RecordNotFoundExcption;
import com.aize.assignment.models.Product;
import com.aize.assignment.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public Product updateProduct(Long id, Product newProductValue) {
        Product product = productRepository.findById(id).orElseThrow(RecordNotFoundExcption::new);
        product.setPrice(newProductValue.getPrice());
        product.setName(newProductValue.getName());
        product.setDescription(newProductValue.getDescription());
        product.setUpdatedDate(new Date());

        return productRepository.saveAndFlush(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page findAll(Pageable pageable, String keyword) {
        return productRepository.findAllByNameContaining(pageable, keyword);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(RecordNotFoundExcption::new);
    }
}
