package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/add")
    public String viewAddTodoPage(Model model) {
        model.addAttribute("todo", new Todo());
        return "add";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        if (todo.getId() != null && !todo.getId().isEmpty() && 
            todo.getDescription() != null && !todo.getDescription().isEmpty()) {
            todos.add(todo);
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String viewEditTodoPage(@PathVariable String id, Model model) {
        Todo todo = todos.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (todo != null) {
            model.addAttribute("todo", todo);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable String id, @ModelAttribute Todo updatedTodo) {
        Todo todo = todos.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (todo != null) {
            todo.setDescription(updatedTodo.getDescription());
            todo.setCompleted(updatedTodo.isCompleted());
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable String id) {
        todos.removeIf(todo -> todo.getId().equals(id));
        return "redirect:/";
    }
}
