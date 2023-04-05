package com.borabesiktepe.isgassist.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyRequest {
	private String address;
	private String city;
	private String mail;
	private String phone;
	private String contactPerson;
	private String contactPersonPhone;
	private int workplaceId;
}
