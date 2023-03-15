package com.loginregistration.authtest.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesResponse {
	private int id;
	private String note;
	private int userId;
}