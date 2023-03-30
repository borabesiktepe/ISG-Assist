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

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String mail;

    @Column
    private String phone;

    @Column
    private String contactPerson;

    @Column
    private String contactPersonPhone;

    @ManyToOne
    private Workplace workplace;
}