package com.study.travly.board.place;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.study.travly.board.Board;
import com.study.travly.board.place.file.BoardPlaceFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false, foreignKey = @ForeignKey(name = "fk_board_place__board_id"))
	private Board board;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private int orderNum;

	@Comment("kakaomap api의 장소 id")
	private String mapPlaceId;

	@Comment("한국관광공사_국문 관광정보 서비스. contentid. https://www.data.go.kr/data/15101578/openapi.do")
	private String externalId;

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

	@OneToMany(mappedBy = "boardPlace", cascade = CascadeType.PERSIST)
	private List<BoardPlaceFile> files;
}
