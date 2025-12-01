package com.study.travly.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class boardController {

	@Autowired
	BoardService boardService;

	@PostMapping("board")
	Optional<Board> createBoard(@RequestBody BoardSaveRequest req) {
		return boardService.saveBoardWithAllDetails(req);
	}

	@GetMapping("board/{id}")
	public Optional<Board> boardListView(@PathVariable("id") Long id) {
		return boardService.findByIdWithPlaces(id);
	}
}
