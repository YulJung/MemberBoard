package com.icia.memberboard.repository;

import com.icia.memberboard.entity.BoardEntity;
import com.icia.memberboard.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    List<BoardEntity> findByBoardTitleIsContaining(String keyword);

    List<BoardEntity> findByMemberEntityIsContaining(MemberEntity member);

    List<BoardEntity> findByMemberEntity(MemberEntity member);

    //navtive query  jpql
//    @Modifying
//    @Query(value = "update BoardEntity b set b.boardView = b.boardView+1 where b.boardId = :boardId")
//    int boardView(@Param("boardId")Long boardId);
}
