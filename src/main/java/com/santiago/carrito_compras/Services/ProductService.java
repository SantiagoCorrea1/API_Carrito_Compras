package com.santiago.carrito_compras.Services;

import com.santiago.carrito_compras.Dto.ProductDto;
import com.santiago.carrito_compras.Entities.Product;
import com.santiago.carrito_compras.Interfaces.ProductInterface;
import com.santiago.carrito_compras.Repositories.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductInterface {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product createProduct(ProductDto dao) {
        Product productNew = new Product();
        productNew.setName(dao.getName());
        productNew.setDescription(dao.getDescription());
        productNew.setImg(dao.getImg());
        productNew.setAvailable(dao.isAvailable());
        productNew.setPrice(dao.getPrice());
        productNew.setAvailableAmount(dao.getAvailableAmount());
        return repository.save(productNew);
    }

    @Override
    public Product updateProductById(ProductDto dao, long id) {
        if (repository.existsById(id)){
            Product product = repository.findById(id)
                    .orElseThrow(() -> new  ObjectNotFoundException("product not found", id));
            product.setDescription(dao.getDescription());
            product.setImg(dao.getImg());
            product.setPrice(dao.getPrice());
            product.setDescription(dao.getDescription());
            product.setAvailableAmount(dao.getAvailableAmount());
            product.setAvailable(dao.isAvailable());
            return repository.save(product);
        } else {
            throw new ObjectNotFoundException("product not found", id);
        }
    }

    @Override
    public void deleteProductById(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductDto> searchAllProducts() {
        List<Product> products = repository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product: products) {
            productDtos.add(product.getDto());
        }
        return productDtos;
    }

    @Override
    public ProductDto findProductInfoById(long id) {
        return repository.findById(id).get().getDto();
    }
    @Override
    public Product findProductById(long id) {
        return repository.findById(id).orElseThrow(null);
    }

    @Override
    public boolean existById(long id) {
        return repository.existsById(id);
    }
}
