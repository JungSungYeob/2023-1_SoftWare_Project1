package com.example.Proj1_2019202021.repository;

import com.example.Proj1_2019202021.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long> { //Interface which Access Crated DB

}
