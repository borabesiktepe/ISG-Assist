package com.borabesiktepe.isgassist.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="workplaces")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Workplace {
	@Id
	@GeneratedValue
	@Column(nullable = false)
	private int id;

	@Column(name="workplace_name")
	private String workplaceName;

	@Column(name="workplace_description")
	private String workplaceDescription;

	@ManyToOne
	private User user;
}