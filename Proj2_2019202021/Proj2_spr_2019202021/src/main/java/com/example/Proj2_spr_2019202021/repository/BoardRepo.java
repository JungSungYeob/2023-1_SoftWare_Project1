package com.example.Proj2_spr_2019202021.repository;

import com.example.Proj2_spr_2019202021.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long> { //Interface which Access Crated DB

}
