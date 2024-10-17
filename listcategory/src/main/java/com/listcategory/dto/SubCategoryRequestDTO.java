package com.listcategory.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class SubCategoryRequestDTO {
    private String name;
    private String size;
    private Integer quantity;
    private MultipartFile image;
}
