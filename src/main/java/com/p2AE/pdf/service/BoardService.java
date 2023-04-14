package com.p2AE.pdf.service;

import com.p2AE.pdf.domain.Board;
import com.p2AE.pdf.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long join(Board board){
        boardRepository.save(board);

        return board.getId();
    }

}
