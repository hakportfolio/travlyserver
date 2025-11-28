package com.study.travly.member;

import java.time.LocalDateTime;
import java.util.UUID;

import com.study.travly.grade.Grade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member", uniqueConstraints = {
		@UniqueConstraint(name = "UK_member_auth_uuid", columnNames = { "auth_uuid" } // DB 컬럼 이름
		) })
@Getter
@Setter
@NoArgsConstructor // default Constructor
@AllArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private UUID auth_uuid;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String alias;

	@Column(nullable = false)
	private int gender; // 1 : man, 2 : woman

	private String introduction;
	private LocalDateTime birthday;

	@ManyToOne
	@JoinColumn(name = "grade_id", nullable = false)
	private Grade grade;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
		this.gender = 1;
		this.introduction = "";
	}

	@PreUpdate
	public void onUpdatedd() {
		this.updatedAt = LocalDateTime.now();
	}
}
