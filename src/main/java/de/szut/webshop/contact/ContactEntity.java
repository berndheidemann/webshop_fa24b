package de.szut.webshop.contact;


import de.szut.webshop.supplier.SupplierEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
@Table(name="supplier_contact")
public class ContactEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="street is mandatory")
    @Size(max=50, message="street must be at most 50 characters long")
    private String street;


    @Column(name="zip")
    @NotBlank(message="postcode is mandatory")
    @Size(min=5, max=5, message="postcode must be 5 characters long")
    private String postcode;

    @NotBlank(message="city is mandatory")
    @Size(max=50, message="city must be at most 50 characters long")
    private String city;

    private String phone;

    @OneToOne(mappedBy = "contact",
            fetch = FetchType.LAZY,
            cascade =  CascadeType.PERSIST)
    private SupplierEntity supplier;
}
