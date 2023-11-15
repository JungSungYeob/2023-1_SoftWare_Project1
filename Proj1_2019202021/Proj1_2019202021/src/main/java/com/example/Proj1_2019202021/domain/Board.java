package com.example.Proj1_2019202021.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor
public class Board { //Board Class
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Table Column Increase Id(Key Value)
    private Long id;
    @Column //Table Column named String
    private String title;
    @Column //Table Column named contents
    private String contents;
    @Column //Table Column named fileName
    private String fileName;
    @Column //Table Column named filePath
    private String filePath;
}
