package net.datasa.web6.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ajax_member")
public class MemberEntity {
	@Id
	@Column(name = "id")
	String id;
	String name;
}
