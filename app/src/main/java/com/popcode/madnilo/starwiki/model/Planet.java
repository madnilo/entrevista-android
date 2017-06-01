package com.popcode.madnilo.starwiki.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by Danilo Lima on 31/05/2017.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet implements Serializable {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
