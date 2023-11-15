package com.example.Proj2_spr_2019202021.service;

import com.example.Proj2_spr_2019202021.domain.Board;
import com.example.Proj2_spr_2019202021.domain.BoardDTO;
import com.example.Proj2_spr_2019202021.repository.BoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardService { //Service Layer
    @Autowired
    private BoardRepo boardRepo;


    public void createBoard(Board board, MultipartFile file)throws Exception{ //Create Board Table with Object Board, throw exception
        if(file.isEmpty()) {
        }else {
            String projectPath = System.getProperty("user.dir") + "/build/resources/main/static";

            //파일의 원래 이름
            String origName = file.getOriginalFilename();
            //랜덤 uuid
            String uuid = UUID.randomUUID().toString();
            //확장자 추출
            String extension = origName.substring(origName.lastIndexOf("."));
            //uuid와 확장자 결합
            String savedName = uuid + extension;
            //새로운 파일 저장
            File saveFile = new File(projectPath, savedName);
            //전송
            file.transferTo(saveFile);
            //h2 database에 저장할 이름과 경로
            board.setFileName(savedName);
            board.setFilePath(savedName);
        }
        boardRepo.save(board);
    }

    public void updateBoard(@ModelAttribute BoardDTO boardDTO, @PathVariable Long id, MultipartFile file)throws Exception{ //Create Board Table with Object Board

        try {
            Optional<Board> updateBoard = boardRepo.findById(id); //Find Data with ID
            Board board1 = updateBoard.get(); //Get
            board1.setContents(boardDTO.getContents()); //Set Board contents with Modify one
            board1.setTitle(boardDTO.getTitle()); //Set Board title with Modify one

            if(file.isEmpty()) {

            }else {
                String projectPath = System.getProperty("user.dir") + "/build/resources/main/static";
                //파일의 원래 이름
                String origName = file.getOriginalFilename();
                //랜덤 uuid
                String uuid = UUID.randomUUID().toString();
                //확장자 추출
                String extension = origName.substring(origName.lastIndexOf("."));
                //uuid와 확장자 결합
                String savedName = uuid + extension;
                //파일 저장
                File saveFile = new File(projectPath, savedName);
                //전송
                file.transferTo(saveFile);

                //h2 database에 저장할 이름과 경로
                board1.setFileName(savedName);
                board1.setFilePath(savedName);
            }
            boardRepo.save(board1); //Save Board
        } catch (Exception e){ //Exception Handling
            e.printStackTrace();
        }
    }

    public void deleteBoard(Long id){ //Delete Data from Board Table with ID
        boardRepo.deleteById(id);
    }
}

