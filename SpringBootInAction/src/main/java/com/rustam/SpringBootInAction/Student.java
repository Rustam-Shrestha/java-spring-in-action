package com.rustam.SpringBootInAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
    @Autowired
    Laptop laptop;
    public void code() {
        System.out.println("coding project");
        laptop.compiling();

    }
}
