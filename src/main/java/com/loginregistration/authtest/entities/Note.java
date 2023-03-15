package com.loginregistration.authtest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(name="note")
    private String note;

    @ManyToOne
    private User user;
}
