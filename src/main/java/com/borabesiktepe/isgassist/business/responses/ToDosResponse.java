package com.borabesiktepe.isgassist.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDosResponse {
	private int id;
	private String todoItem;
	private int userId;
}