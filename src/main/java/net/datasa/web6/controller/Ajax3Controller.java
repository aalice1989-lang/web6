package net.datasa.web6.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web6.service.AjaxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 테스트 컨트롤러
 * 추천, 중복확인, 댓글
 */
@Controller
@Slf4j
@RequiredArgsConstructor
public class Ajax3Controller {

	private final AjaxService ajaxService;
	/**
	 * 추천 페이지로 이동
	 */
	@GetMapping("like")
	public String like(){
		return "like";
	}
	/**
	 * 글 하나 추천 처리
	 * @param boardNum 추천할 글 번호
	 * @return 증가된 추천수
	 */
	@ResponseBody
	@PostMapping("like")
	public int like(@RequestParam("boardNum") Integer boardNum){
		log.debug("추천수 증가할 글 번호:{}",boardNum);
		int likeCount = ajaxService.likeUp(boardNum);
		return likeCount;
	}

	/**
	 * 아이디 중복확인 기능 테스트용 페이지로 이동
	 * @return HTML 파일명
	 */
	@GetMapping("idDuplicate")
	public String idDuplicate(){
		return "idDuplicate";
	}

	/**
	 * 아이디 존재여부 확인
	 *
	 *
	 */
	@ResponseBody
	@PostMapping("idDuplicate")
	public Boolean idDuplicate(@RequestParam("id") String id){
		log.debug("중복확인할 아이디{}", id);
		// 서비스의 메서드를 통해 id 중복확인 하기
		boolean result = ajaxService.idDuplicate(id);
		return result;
	}
}
