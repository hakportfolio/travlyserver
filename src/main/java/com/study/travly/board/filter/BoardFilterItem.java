package com.study.travly.board.filter;

import java.time.LocalDateTime;

import com.study.travly.board.Board;
import com.study.travly.filter.item.Item;

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
@Table(name = "board_filter_item")
@Getter
@Setter
@AllArgsConstructor
public class BoardFilterItem {
	@Id
	@ManyToOne()
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;

	@Id
	@ManyToOne()
	@JoinColumn(name = "filter_item_id", nullable = false)
	private Item filterItem;

	@Column(nullable = false)
	private int order_num;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	public void onCreated() {
		this.createdAt = LocalDateTime.now();
		this.order_num = 0;
	}
}
