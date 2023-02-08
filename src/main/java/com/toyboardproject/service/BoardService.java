package com.toyboardproject.service;

import com.toyboardproject.dto.PrincipalDto;
import com.toyboardproject.repository.BoardRepository;
import com.toyboardproject.domain.Board;
import com.toyboardproject.dto.BoardRequestDto;
import com.toyboardproject.dto.BoardResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 생성
    // account dto객체 매개변수 추가 필요
    public Long createBoard(BoardRequestDto dto, PrincipalDto principal){
        return boardRepository.save(dto.toEntity(principal)).getId();
    }

    // 게시글 단건 조회
    public BoardResponseDto findBoardById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다"));
        return BoardResponseDto.from(entity);
    }
    
    // 게시글 검색
    //public Page<BoardResponseDto> findByBoardTypeAndSearchTypeAndKeyword(Pageable pageable, BoardType boardType, SearchType searchType, String keyword){
            //return switch (searchType) {
            //case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(Article.ArticleDto::from).map(Article.ArticleResponse::from);
            //case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(Article.ArticleDto::from).map(Article.ArticleResponse::from);
            //case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(Article.ArticleDto::from).map(Article.ArticleResponse::from);
            //case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(Article.ArticleDto::from).map(Article.ArticleResponse::from);
    //}

    // 게시글 리스트 조회
    public Page<BoardResponseDto> findAll(Pageable pageable){
        return boardRepository.findAll(pageable).map(BoardResponseDto::from);
    }



    //게시글 수정 (변경점만 수정)
    // accountId 매개변수 추가 필요
    public Long updateBoardById(Long id, BoardRequestDto dto){
        Board entity = boardRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("게시글 없음"));
        //if(!entity.getBoardTitle().equals(dto.getBoardTitle()){
        //entity.setBoardTitle(dto.getBoardTitle());
        //}
        return id;
    }

    //게시글 삭제
    //accountId 매개변수 추가 필요
    public void deleteBoardById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("게시글 없음"));
        boardRepository.deleteById(id);
    }

 


}