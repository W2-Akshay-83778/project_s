package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final List<Todo> todos = new ArrayList<>();


    public List<Todo> getAllTodos() {
        return todos;
    }

    
    public void addTodo(Todo todo) {
        if (todo.getId() != null && !todo.getId().isEmpty() &&
            todo.getDescription() != null && !todo.getDescription().isEmpty()) {
            todos.add(todo);
        }
    }

    public Optional<Todo> findTodoById(String id) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }


    public void updateTodo(String id, Todo updatedTodo) {
        findTodoById(id).ifPresent(todo -> {
            todo.setDescription(updatedTodo.getDescription());
            todo.setCompleted(updatedTodo.isCompleted());
        });
    }

    
    public void deleteTodo(String id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}
