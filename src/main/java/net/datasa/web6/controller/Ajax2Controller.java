package net.datasa.web6.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web6.domain.dto.PersonDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class Ajax2Controller {
	/**
	 * 테스트 페이지2 이동
	 */
	@GetMapping("ajax2")
	public String ajax2(){
		return "ajax2";
	}

	@PostMapping("insert1")
	@ResponseBody
	public void insert1(
			@RequestParam("name") String name,
			@RequestParam("age") String age,
			@RequestParam("phone") String phone
	){
		log.debug("insert1() : name= {}, age= {}, phone= {}", name, age, phone);
	}
	@PostMapping("insert2")
	@ResponseBody
	public void insert2(
			@ModelAttribute PersonDTO person
	){
		log.debug("insert2() : person= {}", person);
		log.debug("insert2() : name= {}, age= {}, phone= {}", person.name, person.age, person.phone);
	}

	/**
	 * FormData 전송 방식
	 *
	 * @param person
	 */
	@PostMapping("insert3")
	@ResponseBody
	public void insert3(
			@ModelAttribute PersonDTO person
	){
		log.debug("insert3() : person= {}", person);
		log.debug("insert3() : name= {}, age= {}, phone= {}", person.name, person.age, person.phone);
	}

	/**
	 * DTO 객체를 클라이언트로 반환
	 *
	 * - 반환값은 ResponseBody에 의해 JSON으로 변환됨
	 */
	@ResponseBody
	@GetMapping("getObject")
	public PersonDTO getObject(){
		return new PersonDTO("홍길동", "20", "00-00000");
	}
	/**
	 * DTO 리스트를 클라이언트로 반환
	 *
	 * - JSON 배열 형태로 응답
	 */
	@ResponseBody
	@GetMapping("getList")
	public ArrayList<PersonDTO> getList(){
		ArrayList<PersonDTO> personDTOS = new ArrayList<>();
		personDTOS.add(new PersonDTO("홍길동", "20", "00-00000"));
		personDTOS.add(new PersonDTO("강감찬", "30", "010-2222-2222"));
		personDTOS.add(new PersonDTO("이순신", "40", "010-3333-3333"));

		return personDTOS;
	}

	/**
	 * 클라이언트로부터 문자열 배열 받음
	 *
	 * - 동일한 파라미터 이름(ar)에 문자열을 담아 여러번 전달하면 spring에서 String[]으로 바인딩
	 */
	@ResponseBody
	@PostMapping("sendArray")
	public void sendArray(@RequestParam(name = "ar") String[] ar){
		for (String s : ar){
			log.debug("배열 요소: {},", s);
		}
	}

	/**
	 * 클라이언트로부터 JSON문자열 형태의 객체 배열 전달
	 * @param ar
	 */
	@ResponseBody
	@PostMapping("sendObjectArray")
	public void sendObjectArray (@RequestParam(name = "ar") String ar) throws JsonProcessingException {
		log.debug("전달받은 JSON문자열 {}", ar);

		// JSON문자열 -> 객체 리스트 변환
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<PersonDTO> list =
				mapper.readValue(ar, new TypeReference<>() {
				});
		log.debug("변환결과: {}" , list);

		for (PersonDTO dto : list){
			log.debug("요소 타입{}", dto.getClass());
			log.debug("요소 값{}", dto);
		}
	}
	/**
	 * 클라이언트가 객체배열(JSON으로 전달)
	 */
	@ResponseBody
	@PostMapping("sendJsonArray")
	public void sendJsonArray(@RequestBody List<PersonDTO> list){
		log.debug("전달받은 객체 리스트: {}" , list);
		for (PersonDTO dto : list){
			log.debug("요소 타입{}", dto.getClass());
			log.debug("요소 값{}", dto);
		}
	}
	// 같은 데이터지만 전송위치(request Parameter vs body)와
	// 바인딩 방식(ModelAttribute/RequestParam vs RequestBody)이 다름

}
