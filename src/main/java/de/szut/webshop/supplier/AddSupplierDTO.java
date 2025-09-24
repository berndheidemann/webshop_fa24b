package de.szut.webshop.supplier;


import de.szut.webshop.contact.ContactEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSupplierDTO {

    private String name;
    private String street;
    private String postcode;
    private String city;
    private String phone;

    public static SupplierEntity toEntity(AddSupplierDTO dto) {
        var entity = new SupplierEntity();
        entity.setName(dto.getName());
        var contact=new ContactEntity();
        contact.setStreet(dto.getStreet());
        contact.setPostcode(dto.getPostcode());
        contact.setCity(dto.getCity());
        contact.setPhone(dto.getPhone());
        entity.setContact(contact);
        return entity;
    }
}
