package com.example.Proj2_spr_2019202021.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoDTO {
    public String id;
    public String title;
    public String contents;
    public String filename;

    public InfoDTO(String id,String title, String contents,String filename){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.filename = filename;
    }
}