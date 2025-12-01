package com.study.travly.board.place.file;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.study.travly.board.place.BoardPlace;
import com.study.travly.file.File;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_place_id", nullable = false, foreignKey = @ForeignKey(name = "fk_board_place_file__board_place_id"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BoardPlace boardPlace;

	@OneToOne()
	@JoinColumn(name = "file_id", nullable = false, foreignKey = @ForeignKey(name = "fk_board_place_file__file_id"))
	private File file;

	@Column(nullable = false)
	private int orderNum; // db keyword 'order' 와 충돌 회피

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
		this.orderNum = 0;
	}
}
