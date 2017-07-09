package ir.suchme.core.domain.entity;

import java.util.*;

/**
 * Created by Farzin on 7/8/2017.
 */
public class Process{

    private Product product;

    private Set<SupplyComponent> supplyComponents = new HashSet<>();

    public Process(Product product, Set<SupplyComponent> supplyComponents) {
        this.product = product;
        this.supplyComponents = supplyComponents;
    }

    public Process(Product product)
    {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<SupplyComponent> getSupplyComponents() {
        return supplyComponents;
    }

    public void setSupplyComponents(Set<SupplyComponent> supplyComponents) {
        this.supplyComponents = supplyComponents;
    }

    public void addToSupplyComponents(SupplyComponent supplyComponent)
    {
        this.supplyComponents.add(supplyComponent);
    }

    @Override
    public String toString() {
        return "Process{" +
                "product=" + product +
                ", supplyComponents=" + supplyComponents +
                '}';
    }


    public static List<Process> makeProcess(Product product)
    {

        if(product.getSubProducts() == null || product.getSubProducts().size() == 0)
        {
            List<Process> out = new LinkedList<>();
            Process p = new Process(product);
            p.setSupplyComponents(product.getSupplyComponents());
            out.add(p);
            return out;
        }

        List<Process> processes = new LinkedList<>();
        for (Product sub : product.getSubProducts())
        {
            processes.addAll(makeProcess(sub));
        }
        Process p2 = new Process(product);
        p2.setSupplyComponents(product.getSupplyComponents());
        processes.add(p2);
        return processes;
    }


}


