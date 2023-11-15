package com.example.Proj1_2019202021.controller;

import com.example.Proj1_2019202021.domain.Board;
import com.example.Proj1_2019202021.domain.BoardDTO;
import com.example.Proj1_2019202021.repository.BoardRepo;
import com.example.Proj1_2019202021.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepo boardRepo;



    @PostMapping("board") //Mapping API POST
    public RedirectView createBoard(@ModelAttribute("board") Board board,
                                    @RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) throws Exception { //Create Board DB Table

        boardService.createBoard(board, file); //create board by boardservice



        return new RedirectView("/"); //redirectview to home
    }

    @PutMapping("board/{id}") //Mapping API PUT
    public RedirectView updateBoard(@PathVariable Long id,
                                    @RequestParam("file") MultipartFile file,
                                    @ModelAttribute("board") BoardDTO boardDTO)throws Exception{ //Modify Board DB Table

        boardService.updateBoard(boardDTO,id,file); //update board by boardservice

        return new RedirectView("/"); //redirectview to home
    }
}
