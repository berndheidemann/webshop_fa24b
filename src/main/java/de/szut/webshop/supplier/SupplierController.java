package de.szut.webshop.supplier;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("suppliers")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public GetSupplierSimpleDTO create(@Valid @RequestBody final AddSupplierDTO dto) {
        var addDtoEntity = AddSupplierDTO.toEntity(dto);
        var savedEntity = supplierService.save(addDtoEntity);
        return GetSupplierSimpleDTO.toDTO(savedEntity);
    }

    @GetMapping("{id}")
    public GetSupplierSimpleDTO get(@PathVariable Long id) {
        var entity = supplierService.readById(id);
        return GetSupplierSimpleDTO.toDTO(entity);
    }
}
