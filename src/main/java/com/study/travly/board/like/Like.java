package com.study.travly.board.like;

import java.time.LocalDateTime;

import com.study.travly.board.Board;
import com.study.travly.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "likes") // db keyword 'like' 와 충돌 회피
@Getter
@Setter
@AllArgsConstructor
public class Like {

	@Id
	@ManyToOne()
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Id
	@ManyToOne()
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
	}

}
