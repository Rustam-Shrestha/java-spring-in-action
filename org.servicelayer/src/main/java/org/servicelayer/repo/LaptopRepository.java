package org.servicelayer.repo;

import org.servicelayer.model.Laptop;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopRepository {
    public void save(Laptop lap){
        System.out.println("saved in databae");
    }
}
