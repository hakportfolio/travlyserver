package com.study.travly.board.comment;

import java.time.LocalDateTime;

import com.study.travly.board.Board;
import com.study.travly.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor // default Constructor
@AllArgsConstructor
public class Comment {
	@Id
	@ManyToOne()
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Id
	@ManyToOne()
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	private String comment;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = this.createdAt;
	}

	@PreUpdate
	public void onUpdatedd() {
		this.updatedAt = LocalDateTime.now();
	}
}
