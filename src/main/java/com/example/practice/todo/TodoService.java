package com.example.practice.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    public final TodoRepository repository;



    public void saveTodo(Todo todo){
        repository.save(todo);
    }
}
