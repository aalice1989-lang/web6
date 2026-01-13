package net.datasa.web6.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datasa.web6.domain.dto.CommentDTO;
import net.datasa.web6.domain.entity.CommentEntity;
import net.datasa.web6.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 댓글용 서비스 클래스
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository repository;

	/**
	 * 댓글 목록 가져오기
	 * @return 댓글목록 DTO list
	 */
	public List<CommentDTO> getList() {
		List<CommentEntity> entityList = repository.findAll();
		List<CommentDTO> dtoList = new ArrayList<>();
		for (CommentEntity entity : entityList) {
			CommentDTO dto = CommentDTO.builder()
					.num(entity.getNum())
					.name(entity.getName())
					.comment(entity.getComment())
					.build();
			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 * 댓글 저장
	 * @param dto	저장할 댓글 정보
	 */
	public void create(CommentDTO dto) {
		CommentEntity entity = CommentEntity.builder()
				.name(dto.getName())
				.comment(dto.getComment())
				.build();

		repository.save(entity);
	}

	/**
	 * 댓글 삭제
	 * @param num 삭제할 댓글의 번호
	 */
	public void delete(Integer num) {
		CommentEntity entity = repository.findById(num)
				.orElseThrow(()-> new EntityNotFoundException("삭제 대상 댓글이 존재하지 않습니다."));
		repository.delete(entity);
	}

	/**
	 * 댓글 수정
	 * @param dto 수정할 댓글 내용
	 */
	public void update(CommentDTO dto) {
		CommentEntity entity = repository.findById(dto.getNum()).orElseThrow(()-> new EntityNotFoundException("수정할 댓글이 없습니다."));

		/*
		* PATCH(부분수정) 요청 기준 검사할 내용
		* - '변경할 값만 전달'하는 요청이므로
		* 값이 존재하는 필드만 선택적으로 반영
		* - null 체크 후 set
		*/
		if (dto.getComment() != null) {
			entity.setComment(dto.getComment());
		}

		/*
		* PUT(전체수정) 요청 기준 검사할 내용
		* - 필수값 누락 여부 (누락값이 있으면 예외 발생)
		* - 변경 불가 컬럼
		* if (dto.getComment()==null){
		* 	throw new IllegalArgumentException("내용은 필수입니다");
		* }
		*/
	}
}
