package com.borabesiktepe.isgassist.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompaniesResponse {
	private int id;
	private String address;
	private String city;
	private String mail;
	private String phone;
	private String contactPerson;
	private String contactPersonPhone;
	private int workplaceId;
}