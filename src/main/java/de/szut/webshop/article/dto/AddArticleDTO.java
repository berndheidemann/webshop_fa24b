package de.szut.webshop.article.dto;


import de.szut.webshop.article.ArticleEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddArticleDTO {

    private String label;
    private Double price;


    public static ArticleEntity toEntity(AddArticleDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setDesignation(dto.getLabel());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
