package com.example.demo.service;

import com.example.demo.model.TodoItem;
import com.example.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<TodoItem> getAllTodos() {
        return todoRepository.findAll();
    }

    public TodoItem saveTodo(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

    public TodoItem getTodoById(long id) {
        Optional<TodoItem> optionalTodo = todoRepository.findById(id);
        return optionalTodo.orElse((null));
    }

    public TodoItem updateTodo(Long id, TodoItem updatedTodo) {
        Optional<TodoItem> optionalTodo = todoRepository.findById(id);

        if(optionalTodo.isPresent()) {
            TodoItem existingTodo = optionalTodo.get();
            existingTodo.setTask(updatedTodo.getTask());
            return todoRepository.save(existingTodo);
        } else {
            return null;
        }
    }

    public boolean deleteTodo(Long id) {
        if(todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
