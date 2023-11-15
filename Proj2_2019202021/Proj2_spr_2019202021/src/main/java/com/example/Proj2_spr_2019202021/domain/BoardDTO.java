package com.example.Proj2_spr_2019202021.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO { //Board
    public String id;
    public String title;
    public String contents;
    public String fileName;
    public String filePath;

}
