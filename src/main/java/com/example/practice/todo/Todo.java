package com.example.practice.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {
    private Long id;
    private  String description;
    private LocalDateTime timeSet;
    private LocalDateTime experationTime;
    private boolean isDone;
    private boolean isExpired;

}
