package de.szut.webshop.supplier;


import de.szut.webshop.contact.ContactEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSupplierSimpleDTO {

    private Long id;
    private String name;
    private String street;
    private String postcode;
    private String city;
    private String phone;

    static GetSupplierSimpleDTO toDTO(SupplierEntity entity) {
        if (entity == null) return null;
        var dto = new GetSupplierSimpleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if (entity.getContact() != null) {
            ContactEntity contact = entity.getContact();
            dto.setStreet(contact.getStreet());
            dto.setPostcode(contact.getPostcode());
            dto.setCity(contact.getCity());
            dto.setPhone(contact.getPhone());
        }
        return dto;
    }

}
