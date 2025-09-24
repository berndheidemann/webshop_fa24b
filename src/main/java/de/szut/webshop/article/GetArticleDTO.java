package de.szut.webshop.article;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GetArticleDTO {

    private Long id;
    private String label;
    private Double price;
    private String supplierName;


    public static GetArticleDTO toDTO(ArticleEntity entity) {
        if (entity == null) return null;
        GetArticleDTO dto = new GetArticleDTO();
        dto.setId(entity.getId());
        dto.setSupplierName(entity.getSupplier().getName());
        dto.setLabel(entity.getDesignation());
        dto.setPrice(entity.getPrice());
        return dto;
    }


}
