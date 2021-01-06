/*
 * BoardApiController.java 2021. 01. 07
 *
 * Copyright 2021 WorksMobile Corp. All rights Reserved.
 * WorksMobile PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.web.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.Board;
import com.web.repository.BoardRepository;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {

	private BoardRepository boardRepository;

	public BoardApiController(BoardRepository boardRepository){
		this.boardRepository = boardRepository;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable){
		Page<Board> boards = boardRepository.findAll(pageable);
		PagedResources.PageMetadata pageMetadata =new PagedResources.PageMetadata(pageable.getPageSize(),boards.getNumber(),boards.getTotalElements());
		PagedResources<Board> resources = new PagedResources<>(boards.getContent(), pageMetadata);
		// HATEOS BoardApiController.class의 메소드 링크 제공
		resources.add(linkTo(methodOn(BoardApiController.class).getBoards(pageable)).withSelfRel());
		return ResponseEntity.ok(resources);
	}

	@PostMapping
	public ResponseEntity<?> postBoard(@RequestBody Board board) {
		//valid 체크
		board.setCreatedDateNow();
		boardRepository.save(board);
		return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}

	@PutMapping("/{idx}")
	public ResponseEntity<?> putBoard(@PathVariable("idx")Long idx, @RequestBody Board board) {
		//valid 체크
		Board persistBoard = boardRepository.getOne(idx) ;
		persistBoard.update(board);
		boardRepository.save(persistBoard);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}

	@DeleteMapping("/{idx}")
	public ResponseEntity<?> deleteBoard(@PathVariable("idx")Long idx) {
		//valid 체크
		boardRepository.deleteById(idx);
		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}
