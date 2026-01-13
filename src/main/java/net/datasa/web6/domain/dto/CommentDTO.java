package net.datasa.web6.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 댓글정보 DTO
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	Integer num;
	String name;
	String comment;
}
