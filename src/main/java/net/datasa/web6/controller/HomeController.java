package net.datasa.web6.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인
 */
@Controller
@Slf4j
public class HomeController {

	/**
	 * 메인화면으로 이동
	 */
	@GetMapping({"", "/"})
	public String home() {
		log.debug("메인화면진입");
		return "home";
	}

	@GetMapping("commentView")
	public String commentView(){
		log.debug("댓글화면");
		return "comment";
	}
}
