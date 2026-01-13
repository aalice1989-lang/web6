package net.datasa.web6.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web6.domain.dto.CommentDTO;
import net.datasa.web6.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 댓글 리소스 REST 요청 처리 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
// 클래스 내의 모든 메서드에 @ResponseBody 적용
public class CommentRestController {

	private final CommentService service;

	/**
	 * 댓글 목록 조회
	 * @return 댓글목록
	 */
	@GetMapping
	public List<CommentDTO> getList() {
		// 서비스클래스가 리포지토리를 통해 DB에 접속하여 목록 가져옴
		return service.getList();
	}

	/**
	 * 댓글 저장
	 * @param dto 저장할 댓글 정보
	 */
	@PostMapping
	public void create(@RequestBody CommentDTO dto) {
		log.debug("저장할 댓글 정보 :{}", dto);

		service.create(dto);
	}

	/**
	 * 댓글 삭제
	 *
	 * @param num 삭제할 댓글 번호
	 */
	@DeleteMapping("{num}")
	public void delete(@PathVariable("num") Integer num) {
		service.delete(num);
	}

	/**
	 * 댓글 수정
	 *@param num 수정할 댓글 번호
	 */
	@PatchMapping("{num}")
	public void update(@PathVariable("num") Integer num, @RequestBody CommentDTO dto) {
		log.debug("수정할 댓글 정보: {}", dto);
		dto.setNum(num);	// URI 기준으로 수정대상을 강제
		service.update(dto);
	}
}
