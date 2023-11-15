package com.example.Proj1_2019202021.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO { //Board
    private String id;
    private String title;
    private String contents;
    private String fileName;
    private String filePath;
}
