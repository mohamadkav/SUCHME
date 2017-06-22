package ir.suchme.core.catalogue;

import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Farzin on 6/21/2017.
 */
@Component
public class SupplierCatalogue {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierCatalogue(SupplierRepository supplierRepository)
    {
        this.supplierRepository = supplierRepository;
    }

    public void addSupplier(String name)
    {
        Supplier supplier = new Supplier(name);
        supplierRepository.save(supplier);
    }

    public Supplier findSupplier(String name)
    {
        return supplierRepository.findFirstByName(name);
    }

    public Iterable<Supplier> findAll(){

        return supplierRepository.findAll();
    }

    public void deleteSupplier(Supplier supplier)
    {
        supplierRepository.delete(supplier);
    }



}
