package de.szut.webshop.article;


import de.szut.webshop.supplier.SupplierEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="article")
public class ArticleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="designation is mandatory")
    private String designation;

    @NotNull
    private Double price;


    @Column(name="create_date", nullable = false)
    private LocalDate createDate= LocalDate.now();

    @Column(name="last_update_date", nullable = false)
    private LocalDate updateDate= LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private SupplierEntity supplier;

}
