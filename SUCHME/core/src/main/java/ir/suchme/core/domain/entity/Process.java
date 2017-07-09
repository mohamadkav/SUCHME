package ir.suchme.core.domain.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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


    public List<Process> makeProcess(Product product)
    {
        Stack<Process> queue = new Stack<>();
        for (Product sub : product.getSubProducts())
        {
//            queue.push()
        }
        return null;
    }
}


