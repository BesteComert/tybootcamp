package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Category;
import com.tybootcamp.ecomm.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> getCategoryByName(@RequestParam(value = "name") String name) {
        if(name == null){
            List<Category> allCategories = categoryRepository.findAll();
            return new ResponseEntity<>(allCategories, HttpStatus.OK);
        }

        List<Category> categoryList = categoryRepository.findAllByName(name);
        if (!categoryList.isEmpty()) {
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "There isn't any Category with name: " +
                        name, HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/")
    public Object addNewCategory(@RequestParam(value = "name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }
        Category createdCategory = new Category(name.trim());
        createdCategory = categoryRepository.save(createdCategory);
        return createdCategory;
    }

    @PutMapping(path = "/")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category) {
        if (category == null) {
            return new ResponseEntity<>("Your request is null!", HttpStatus.BAD_REQUEST);
        }
        try {
            Category categoryEntity = categoryRepository.findById(category.getId()).orElseThrow(EntityNotFoundException::new);
            categoryEntity.setName(category.getName());
            categoryRepository.save(categoryEntity);
            return new ResponseEntity<>("The category updated", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("This category does not exists", HttpStatus.NOT_FOUND);
        }
    }
}
