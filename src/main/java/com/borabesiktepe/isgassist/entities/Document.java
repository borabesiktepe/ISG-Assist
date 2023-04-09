package com.borabesiktepe.isgassist.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="documents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Document {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    private String fileName;

    @ManyToOne
    private Workplace workplace;
}
