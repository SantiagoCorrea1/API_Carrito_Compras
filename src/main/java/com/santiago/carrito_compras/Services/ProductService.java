package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Interfaces.ProductInterface;
import com.santiago.carrito_compras.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductInterface {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Product updateProductById(Product productUpdated, long id) {
        if (repository.existsById(id)){
            Product product = repository.findById(id).orElse(null);
            product.setDescription(productUpdated.getDescription());
            product.setImg(productUpdated.getImg());
            product.setPrice(productUpdated.getPrice());
            product.setDescription(product.getDescription());
            return repository.save(product);
        }
        return null;
    }

    @Override
    public void deleteProductById(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> searchAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product findProductById(Product product) {
        return repository.findById(product.getId()).orElse(null);
    }
}
