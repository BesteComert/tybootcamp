package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Category;
import com.tybootcamp.ecomm.entities.Product;
import com.tybootcamp.ecomm.entities.Seller;
import com.tybootcamp.ecomm.repositories.CategoryRepository;
import com.tybootcamp.ecomm.repositories.ProductJpaRepository;
import com.tybootcamp.ecomm.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductJpaRepository productJpaRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductJpaRepository productJpaRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository) {
        this.productJpaRepository = productJpaRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(path = "/")
    public List<Product> getAllProducts()
    {
        return productJpaRepository.findAll();
    }

    @PostMapping(path = "/")
    public Object addNewProduct(@RequestBody Product product)
    {
        //Check the constraints
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }
        if (product.getImages() == null || product.getImages().size() == 0) {
            return HttpStatus.BAD_REQUEST;
        }

        Seller seller;
        try {
            seller = sellerRepository.findById(product.getSeller().getId()).orElseThrow(EntityNotFoundException::new);
        }
        catch (EntityNotFoundException e) {
            return HttpStatus.BAD_REQUEST;
        }

        HashSet<Category> categories = new HashSet<>();
        try {
            for (Category category : product.getFallIntoCategories()) {
                categories.add(categoryRepository.findById(category.getId()).orElseThrow(EntityNotFoundException::new));
            }
        }
        catch (EntityNotFoundException e) {
            return HttpStatus.BAD_REQUEST;
        }

        if (!categories.isEmpty()) {
            Product createdProduct = new Product(product.getName(),product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getImages(),
                    seller,
                    categories);
            createdProduct = productJpaRepository.save(createdProduct);
            System.out.println("A new Product created with id: " + createdProduct.getId() + "  and name: " + createdProduct.getName());
            return createdProduct;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping(path = "/")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody Product product)
    {
        Product productEntity;
        Seller seller;
        try
        {
            productEntity = productJpaRepository.getOne(product.getId());
            System.out.println("The product " + productEntity.getName() + " with id " + productEntity.getId() + " is updating...");
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>("This product does not exists.", HttpStatus.NOT_FOUND);
        }
        try {
            seller = sellerRepository.getOne(product.getSeller().getId());
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The seller does not exists", HttpStatus.NOT_FOUND);
        }
        HashSet<Category> categories = new HashSet<>();
        for (Category category : product.getFallIntoCategories()) {
            categoryRepository.findById(category.getId()).ifPresent(categories::add);
        }
        if (!categories.isEmpty()) {
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setImages(product.getImages());
            productEntity.setSeller(seller);
            productEntity.setFallIntoCategories(categories);
            productJpaRepository.save(productEntity);
            return new ResponseEntity<>("The product updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("The product must belongs to at least one category!", HttpStatus.BAD_REQUEST);
        }
    }
}
