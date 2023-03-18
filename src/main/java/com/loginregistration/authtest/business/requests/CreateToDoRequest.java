package com.loginregistration.authtest.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateToDoRequest {
	private String todoItem;
	private int userId;
}
