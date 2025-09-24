package de.szut.webshop.supplier;


import de.szut.webshop.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private SupplierRepository repository;

    public SupplierService (SupplierRepository repository) {
        this.repository = repository;
    }

    public SupplierEntity save(SupplierEntity entity) {
        return repository.save(entity);
    }

    public SupplierEntity readById(Long id) {
        var entity= repository.findById(id).orElse(null);
        if (entity==null){
            throw new ResourceNotFoundException(id);
        }
        return entity;
    }
}
