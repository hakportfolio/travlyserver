package com.study.travly.board.place;

import java.time.LocalDateTime;

import com.study.travly.board.Board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board_place")
@Getter
@Setter
@AllArgsConstructor
public class BoardPlace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "board_id", nullable = false, foreignKey = @ForeignKey(name = "fk_board_place_board_id"))
	private Board board;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private int order_num;

	private String mapPlaceId;
	@Column(nullable = false)
	private double x; // 경도(Longitude)

	@Column(nullable = false)
	private double y; // 위도(Latitude)

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
