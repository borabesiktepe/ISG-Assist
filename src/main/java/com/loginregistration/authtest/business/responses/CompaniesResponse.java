package com.loginregistration.authtest.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompaniesResponse {
	private int id;
	private String address;
	private String mail;
	private String phone;
	private String contactPerson;
	private int workplaceId;
}