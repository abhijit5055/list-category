package com.listcategory.controller;

import com.listcategory.dto.SubCategoryRequestDTO;
import com.listcategory.entity.Category;
import com.listcategory.entity.SubCategory;
import com.listcategory.repository.SubCategoryRepository;
import com.listcategory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/subcategories/{id}/details")
    public ResponseEntity<SubCategory> updateSubCategoryDetails(
            @PathVariable Long id,
            @ModelAttribute SubCategoryRequestDTO subCategoryRequestDTO) {

        SubCategory subCategory = subCategoryRepository.findById(id).orElse(null);
        if (subCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the subcategory details
        subCategory.setName(subCategoryRequestDTO.getName());
        subCategory.setSize(subCategoryRequestDTO.getSize());
        subCategory.setQuantity(subCategoryRequestDTO.getQuantity());

        // Handle image upload
        if (subCategoryRequestDTO.getImage() != null && !subCategoryRequestDTO.getImage().isEmpty()) {
            try {
                subCategory.setImage(subCategoryRequestDTO.getImage().getBytes());
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        SubCategory updatedSubCategory = subCategoryRepository.save(subCategory);
        return new ResponseEntity<>(updatedSubCategory, HttpStatus.OK);
    }
    @GetMapping("/subcategories/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElse(null);
        if (subCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subCategory, HttpStatus.OK);
    }


}

