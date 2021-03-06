package edu.kosmo.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.kosmo.ex.service.BoardService;
import edu.kosmo.ex.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@RestController
@RequestMapping("/rest/*")
public class RestBoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 1. list(처음 진입 화면이므로 화면이 깜박여도 상관없으므로 @Controller방식으로 접근 - veiw(화면)를 리턴
	@GetMapping("/board")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("rest/rest_list");
		mav.addObject("list", boardService.getList());
		
		return mav;
	}
	
	@GetMapping("/board/list")
	public List<BoardVO> boardList() {
		log.info("/board/list");
		List<BoardVO> list = boardService.getList();
		return list;
	}
	
	@GetMapping("/board/ajaxList")
	public ModelAndView ajaxList(ModelAndView mav) {
		mav.setViewName("rest/ajaxList");
		
		return mav;
	}
	
	@DeleteMapping("/board/{bid}")
	public ResponseEntity<String> rest_delete(BoardVO boardVO, Model model) {
		ResponseEntity<String> entity = null;
		log.info("rest_delete..");
		try {
			boardService.remove(boardVO.getBid());
			// 삭제가 성공하면 성공 상태메시지 저장
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 댓글 삭제가 실패하면 실패 상태메시지 저장
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// 삭제 처리 HTTP 상태 메시지 리턴
		return entity;	   
	}	
	
}
