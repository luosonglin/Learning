package com.love.lavender.domain;

import java.io.Serializable;

/**
 * Created by luosonglin on 24/03/2018.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -1L;

    private long id;
    private String name;

    public User() {
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


