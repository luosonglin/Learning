package com.love.lavender.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by luosonglin on 24/03/2018.
 */
@Component
public class PersonProperties {

    @Value("${com.love.lavender.name}")
    private String name;

    @Value("${com.love.lavender.dear}")
    private String dear;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDear() {
        return dear;
    }

    public void setDear(String dear) {
        this.dear = dear;
    }
}
