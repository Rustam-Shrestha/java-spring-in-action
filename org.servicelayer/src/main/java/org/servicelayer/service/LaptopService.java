package org.servicelayer.service;
import org.servicelayer.service.LaptopRepository;

import org.servicelayer.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//@component and @service are almost the same
//both will make it managed bean and if it is domain specific programming
// WHEN ANNOTATION SERVICE IS USED WE CAN UNDERSTANT IT IS SERVICE CLASS
@Service
public class LaptopService {
    @Autowired
    private LaptopRepository lrepo;
    public void addLaptop(Laptop lap){
    lrepo.save(lap);
//        System.out.println("laptopservice called");
    }
    public boolean isGoodForProgramming(Laptop lap){
        return true;
    }
}
