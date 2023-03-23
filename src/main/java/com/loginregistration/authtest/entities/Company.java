package com.loginregistration.authtest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String contactPerson;

    @ManyToOne
    private Workplace workplace;
}