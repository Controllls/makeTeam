package com.p2AE.pdf.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.p2AE.pdf.domain.Board;
import com.p2AE.pdf.dto.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired BoardRepository boardRepository;

    @Test
    public void 관례테스트(){
        Board b1 = new Board();
        Board b2 = new Board();
        b1.setContent("AAA");
        b1.setTitle("AAA");
        b2.setTitle("AAA");
        boardRepository.save(b1);
        boardRepository.save(b2);


        List<Board> boards = boardRepository.findBoardsByTitle("AAAg"); // null이면 empty []
        System.out.println("board = " + boards);

        Board board = boardRepository.findBoardByTitle("AAA2"); //
        System.out.println("board = " + board);

        Optional<Board> boardOptional = boardRepository.findOptionalByTitle("AAAg"); // null일수도 아닐수도있으면 옵셔널 써라
        System.out.println("board = " + boardOptional); //.OrElse 어쩌구


//
//        List<Board> result = boardRepository.findByContent("AAA");



    }

    @Test
    public void NameQueryTest(){
        Board b1 = new Board();
        Board b2 = new Board();
        b1.setContent("AAA");
        boardRepository.save(b1);
        boardRepository.save(b2);

        List<Board> result = boardRepository.findBoard("AAA");



    }
    @Test
    public void testBoard(){
        System.out.println("BoardRopository= " + boardRepository.getClass());
        Board board = new Board();
        Board saveBoard = boardRepository.save(board);

        Board findBoard = boardRepository.findById(saveBoard.getId()).get();

        assertSame(findBoard.getId() , board.getId());
        assertSame(findBoard.getContent() , board.getContent());
        assertSame(findBoard , board);



    }

    @Test
    public void findMemberDto(){
        Board b1 = new Board();
        b1.setContent("aaa");
        b1.setTitle("AAA");

        Board board = boardRepository.findBoardByTitle("AAA");

//        List<BoardDto> boardDtos = boardRepository.findBoardDto();
//        for(BoardDto dto : boardDtos){
//            System.out.println("dto = " + dto);
//        }
    }
}