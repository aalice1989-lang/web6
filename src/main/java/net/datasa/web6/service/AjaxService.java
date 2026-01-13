package net.datasa.web6.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web6.domain.entity.BoardEntity;
import net.datasa.web6.repository.BoardRepository;
import net.datasa.web6.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AjaxService {

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;
	/**
	 * 추천수 증가 처리
	 * @param boardNum 추천할 글번호
	 * @return			증가된 추천수
	 */
	public int likeUp(Integer boardNum) {
		// 게시글 엔티티 불러오기
		BoardEntity entity = boardRepository.findById(boardNum)
				.orElseThrow(()->new EntityNotFoundException("게시글이 없습니다."));
		// 게시글 엔티티의 추천수 읽어오기
		int n = entity.getCnt();
		n++;
		// 게시글 엔티티 추천수 변경하기
		entity.setCnt(n);
		return n;
	}

	/**
	 * 아이디 존재 여부 확인
	 * @param id 조회할 아이디
	 * @return	아이디가 존재하면 true, 없으면 false
	 */
	public boolean idDuplicate(String id) {
		return memberRepository.existsById(id);
	}
}
