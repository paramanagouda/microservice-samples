package com.pc.msa.spring.galleryservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gallery {

    private int id;
    private List<Object> images;
    private String errorMessage;

    Gallery(int id){
        this.id = id;
    }

    public Gallery(int id, String errorMessage){
        this.id = id;
        this.errorMessage = errorMessage;
    }
}
