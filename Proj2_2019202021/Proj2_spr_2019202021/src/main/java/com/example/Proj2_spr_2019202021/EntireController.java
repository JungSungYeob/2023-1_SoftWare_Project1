package com.example.Proj2_spr_2019202021;

import com.example.Proj2_spr_2019202021.domain.Board;
import com.example.Proj2_spr_2019202021.repository.BoardRepo;
import com.example.Proj2_spr_2019202021.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class EntireController { //Main Controller
    @Autowired
    private BoardRepo boardRepo;

    @Autowired
    private BoardService boardService;

    @GetMapping("Upload") //Mapping API GET
    public String create(Model model){ //Create Function
        model.addAttribute("board", new Board()); //Deliver Data
        return "Upload"; //create.html
    }

    @GetMapping({"/", "/index.html"}) //Mapping API GET
    public String page(Model model, @RequestParam(required = false) Integer page) { //Pagination Function
        List<Board> boardList = new ArrayList<>();


        boardList = boardRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("boardList", boardList);
        return "Index"; //index.html
    }

    @GetMapping(path = "/ImageView/{id}") //Mapping API GET
    public String viewer(Model model, @PathVariable Long id){ //Read Function
        Optional<Board> board = boardRepo.findById(id); //Find Data with ID
        Board boardEntity = board.get(); //Get

        Board boardAfterSave = boardRepo.save(boardEntity);
        model.addAttribute("board", boardAfterSave); //Deliver Data
        return "ImageView"; //viewer.html
    }

    @GetMapping(path = "/ImageView/update/{id}") //Mapping API GET
    public String viewerUpdate(Model model, @PathVariable Long id) { //Modify Function
        Optional<Board> board = boardRepo.findById(id); //Find Data with ID
        model.addAttribute("board", board.get()); //Deliver Data
        return "Upload"; //update.html
    }

    @PostMapping(path = "/ImageView/{id}") //Mapping API POST
    public String remove(@PathVariable("id") Long id){ //Remove Function
        boardService.deleteBoard(id); //Delete Data with ID
        return "redirect:/"; //index.html
    }
}
