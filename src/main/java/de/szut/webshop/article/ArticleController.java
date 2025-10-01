package de.szut.webshop.article;


import de.szut.webshop.article.dto.AddArticleDTO;
import de.szut.webshop.article.dto.GetArticleDTO;
import de.szut.webshop.article.dto.PatchArticleDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("articles/{id}")
    public GetArticleDTO get(@PathVariable  Long id, @RequestParam(required = false) String currency) {
        var entity=articleService.readById(id, currency);
        return GetArticleDTO.toDTO(entity);
    }

    // localhost:8080/suppliers/1337/articles { label="Sample", price=42.0 }
    @PostMapping("suppliers/{supplierId}/articles")
    @ResponseStatus(code = HttpStatus.CREATED)
    public GetArticleDTO create(@PathVariable Long supplierId, @RequestBody AddArticleDTO dto) {
        var entity = AddArticleDTO.toEntity(dto);
        var savedEntity = articleService.save(supplierId, entity);
        return GetArticleDTO.toDTO(savedEntity);
    }

    @GetMapping("articles")
    @Transactional
    public Set<GetArticleDTO> getAll() {
        var entities = articleService.readAll();
        return entities.stream().map(GetArticleDTO::toDTO).collect(Collectors.toSet());
    }

    @DeleteMapping("articles/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }

    // PATCH localhost:8080/articles/1 { price=99.0 }
    @PatchMapping("articles/{id}")
    public GetArticleDTO patch(@PathVariable Long id, @RequestBody PatchArticleDTO dto) {
        var entity = articleService.readById(id);
        if (dto.getLabel()!=null && dto.getLabel().length()>0) {
            entity.setDesignation(dto.getLabel());
        }
        if (dto.getPrice()!=null) {
            entity.setPrice(dto.getPrice());
        }
        var savedEntity = articleService.save(entity);
        return GetArticleDTO.toDTO(savedEntity);
    }



}
