package com.borabesiktepe.isgassist.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="todo_lists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToDo {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    @Column(name="todo_item")
    private String todoItem;

    @ManyToOne
    private User user;
}
