package net.datasa.web6.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Ajax 테스트 컨트롤러
 */
@Controller
@Slf4j
public class Ajax1Controller {
	/**
	 * 테스트 페이지 1로 이동
	 */
	@GetMapping("ajax1")
	public String ajax1() {
		return "ajax1";
	}

	/**
	 * Ajax 요청에 대한 응답
	 */
	@GetMapping("ajaxtest1")
	@ResponseBody
	public void ajaxtest1() {
		log.debug("ajaxcontroller의 ajaxtest1()실행");
		// 1. 정상실행 확인
//		return;
		// 반환값과 상태코드를 지정하지 않으면 Spring MVC의 기본규칙에 따라 200 OK로 처리된다.
		// 2. 예외 발생
		throw new RuntimeException("서버에서 실행중 예외 발생");
		// 예외를 처리하지 않으면 Spring MVC의 기본규칙에 따라 500응답이 된다
	}

	/**
	 * 요청에 대해 텍스트 반환
	 */
	@GetMapping("ajaxtest2")
	@ResponseBody
	public String ajaxtest2() {
		log.debug("ajaxtest2() 메서드 실행 요청 받음");
		return "OK";
	}

	/**
	 * post요청
	 */
	@ResponseBody
	@PostMapping
			("ajaxtest3")
	public String ajaxtest3(@RequestParam(name = "str") String str) {
		log.debug("Ajaxtest3에서 출력: {}", str);
		return "서버가 다시 보내는 값, 클라이언트가 서버로 전달했던 값은{" + str + "}  ";
	}

	/**
	 * ajax요청에 대한 응답4
	 *
	 * @param a 계산할 정수 1
	 * @param b 계산할 정수 2
	 * @return 전달받은 정수를 더한 결과
	 *
	 */
	@PostMapping("ajaxtest4")
	@ResponseBody
	public int ajaxtest4(@RequestParam("num1") int a, @RequestParam("num2") int b) {
		log.debug("ajaxtest4전달받은 값 num1= {}, num2={} ", a, b);
		return a + b;
	}

	@PostMapping("ajaxtest5")
	@ResponseBody
	public ResponseEntity<?> ajaxtest5(@RequestParam("num4") String a, @RequestParam("num5") String b) {
		log.debug("ajaxtest5전달받은 값 num4= {}, num5={} ", a, b);

		try {
			int n1 = Integer.parseInt(a);
			int n2 = Integer.parseInt(b);
			int n3 = n1 / n2;

			// HTTP 200과 계산결과 정수를 담아서 리턴
			return ResponseEntity.ok(n3);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// HTTP 400과 에러메세지를 본문으로 리턴
			return ResponseEntity.badRequest().body("정수가 아닙니다");
		} catch(ArithmeticException e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body("0으로 나눌 수 없습니다");
		}
	}

	@PostMapping("ajaxtest6")
	@ResponseBody
	public ResponseEntity<?> ajaxtest6(
			@RequestParam("num7") String a
			, @RequestParam("num8") String b
			, @RequestParam("op") String op) {
		log.debug("ajaxtest6전달받은 값 num7= {}, num8={}, op={} ", a, b,op);

		try {
			int n1 = Integer.parseInt(a);
			int n2 = Integer.parseInt(b);
			int n3 = 0;

			switch (op){
				case "/":
					n3 = n1 / n2;
					break;
				default:
					return ResponseEntity.badRequest().body("연산자를 확인해주세요");
			}

			// HTTP 200과 계산결과 정수를 담아서 리턴
			return ResponseEntity.ok(n3);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// HTTP 400과 에러메세지를 본문으로 리턴
			return ResponseEntity.badRequest().body("정수가 아닙니다");
		} catch(ArithmeticException e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body("0으로 나눌 수 없습니다");
		}
	}

}
