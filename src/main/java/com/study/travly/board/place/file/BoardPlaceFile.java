package com.study.travly.board.place.file;

import java.time.LocalDateTime;

import com.study.travly.board.place.BoardPlace;
import com.study.travly.file.File;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board_place_file", uniqueConstraints = {
		@UniqueConstraint(name = "UK_board_place_file_board_place_id_file_id", columnNames = { "board_place_id",
				"file_id" }) })
@Getter
@Setter
@AllArgsConstructor
public class BoardPlaceFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "board_place_id", nullable = false)
	private BoardPlace boardPlace;

	@ManyToOne()
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	@Column(nullable = false)
	private int order_num; // db keyword 'order' 와 충돌 회피

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
		this.order_num = 0;
	}
}
