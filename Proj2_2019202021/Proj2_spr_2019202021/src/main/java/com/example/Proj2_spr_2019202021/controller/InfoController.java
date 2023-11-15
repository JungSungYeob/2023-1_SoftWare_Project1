package com.example.Proj2_spr_2019202021.controller;

import com.example.Proj2_spr_2019202021.domain.Board;
import com.example.Proj2_spr_2019202021.domain.InfoDTO;
import com.example.Proj2_spr_2019202021.repository.BoardRepo;
import com.example.Proj2_spr_2019202021.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class InfoController {

    @Autowired
    private BoardRepo boardRepo;
    @Autowired
    private BoardService boardService;


    //모든 정보 가져오기
    @GetMapping("/Image")
    public List<InfoDTO> getAllPhotos(){
        List<Board> list = new ArrayList<>();
        list = boardRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));//모든 boardRepo 가져오기, 역순

        List<InfoDTO> AllPhotos = new ArrayList<>();//InfoDTO로 리스트

        for(Board element: list){//모든 요소를 사용할 때까지 반복
            AllPhotos.add(new InfoDTO(element.getId().toString(), element.getTitle(), element.getContents(), element.getFileName()));
        }
        return AllPhotos; //반환
    }

    //하나의 정보 가져오기
    @GetMapping("/Image/{id}")
    public InfoDTO infoDTO(@PathVariable("id") Long id){
        Optional<Board> board = boardRepo.findById(id); //해당 id로 가져오기
        Board boardEntity = board.get();
        Board boardAfterSave = boardRepo.save(boardEntity);
        //json 생성하기
        InfoDTO photos = new InfoDTO(boardAfterSave.getId().toString(),boardAfterSave.getTitle(),boardAfterSave.getContents(),boardAfterSave.getFileName());
        return photos;
    }



}
