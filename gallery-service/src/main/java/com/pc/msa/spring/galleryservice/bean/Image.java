package com.pc.msa.spring.galleryservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private int id;
    private String name;
    private String imageUrl;

}
