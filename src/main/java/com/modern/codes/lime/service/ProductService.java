package com.modern.codes.lime.service;

import com.modern.codes.lime.exception.NotFoundException;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Jpa product service
 *
 * @author jaroszk
 *
 */

@Service
@Transactional
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;
    

    public List<Product> findByName(String name){
        Optional<List<Product>> locations = Optional.ofNullable((StringUtils.isEmpty(name) ?
                null : productRepository.findByName(name)));
        if(!locations.isPresent())
            throw new NotFoundException("Product object could not be found in DB");
        return locations.get();
    }
    

    public List<Product> findAll(){
        Optional<List<Product>> products = Optional.ofNullable(productRepository.findAll());
        if(!products.isPresent())
            throw new NotFoundException("Product object could not be found in DB");
        return products.get();
    }

    public Product delete(String id){
        Optional<Product> product = Optional.ofNullable(productRepository.findOne(id));
        if(!product.isPresent())
            throw new NotFoundException("Product object could not be found in DB");
        productRepository.delete(id);
        return product.get();
    }

    public void saveOrUpdate(Product product){
        Optional.ofNullable(this.findByName(product.getName())).ifPresent(data -> {
            this.productRepository.delete(data);
        } );
        productRepository.save(product);
    }

    public void saveAll(List<Product> products){
        products.forEach(product -> {
            Optional.ofNullable(productRepository.findByName(product.getName())).ifPresent(data -> {
                this.productRepository.delete(data);
            } );
        });
        productRepository.save(products);
    }
    
}
