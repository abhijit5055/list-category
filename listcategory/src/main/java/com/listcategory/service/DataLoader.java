package com.listcategory.service;
import com.listcategory.entity.Category;
import com.listcategory.entity.SubCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryService categoryService;

    public DataLoader(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setName("3D Miniatures");

        SubCategory sub1 = new SubCategory();
        sub1.setName("3D Moon");

        SubCategory sub2 = new SubCategory();
        sub2.setName("Heart Lamp");

        SubCategory sub3 = new SubCategory();
        sub3.setName("Dual Name");

        SubCategory sub4 = new SubCategory();
        sub4.setName("Couple Gifts");

        SubCategory sub5 = new SubCategory();
        sub5.setName("God Idol");

        category.setSubCategories(Arrays.asList(sub1, sub2, sub3, sub4, sub5));

        categoryService.createCategory(category);
    }
}
