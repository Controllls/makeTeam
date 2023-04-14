package com.p2AE.pdf.repository;

import com.p2AE.pdf.domain.Board;
import com.p2AE.pdf.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    List<Board> findByContent(String content); // 관례(간단한것들)

    //JPA NamedQuery 파싱을 한번해줘서 문자열보단 오류잡기 쉽다
    @Query(name = "Board.findByContent") //빼도 실행은됨 , 두번쨰 우선순위로 관례사용
    List<Board> findByContent(@Param("content") String content);

    @Query("select m from Board m where m.content = :content")
    List<Board> findBoard(@Param("content") String content);

    @Query("select m.content from Board m")
    List<String> findBoardContentList();

//    @Query("select new com.p2AE.pdf.dto.BoardDto(m.content , m.title) from Board m")
//    List<BoardDto> findBoardDto();

//    @Query("select m from Board where m.content in :str") //in
//    List<Board> findByStr(@Param("str")Collection<String> str);


    List<Board> findBoardsByTitle(String title); //컬랙션
    Board findBoardByTitle(String title);   //단건
    Optional<Board> findOptionalByTitle(String title); // 단건 Optional


}
