package de.szut.webshop.supplier;


import de.szut.webshop.article.ArticleEntity;
import de.szut.webshop.contact.ContactEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="supplier")
public class SupplierEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="name is mandatory")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ContactEntity contact;


    @OneToMany(mappedBy = "supplier",
            fetch = FetchType.EAGER)
    private List<ArticleEntity> articles;
}
