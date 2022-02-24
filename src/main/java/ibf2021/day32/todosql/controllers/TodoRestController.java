package ibf2021.day32.todosql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2021.day32.todosql.models.Todo;
import ibf2021.day32.todosql.repositories.TodoRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoRestController {
    @Autowired
    TodoRepository todoRepo;

    @GetMapping(path="/todos")
    public ResponseEntity<String> getAllTodo() {

        List<Todo> todos = todoRepo.getAllTodos();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        todos.stream().forEach(v -> jab.add(v.toJson()));
        JsonArray ja = jab.build();
        return ResponseEntity.ok(ja.toString());
    }

    @PostMapping(path="/todos")
    public ResponseEntity<String> addTodo(@RequestBody String body) {
        System.out.println(body);
        Todo todo = Todo.populateFromJsonString(body);
        Boolean doesUserExist = todoRepo.doesUserExist(todo.getUsername());
        if (!doesUserExist) {
            JsonObject jo = Json.createObjectBuilder().add("message", "error! user does not exist").build();
            return ResponseEntity.status(HttpStatus.valueOf(401)).body(jo.toString());
        }

        Boolean isAdded = todoRepo.addTodo(todo);
        if (isAdded) {
            JsonObject jo = Json.createObjectBuilder().add("message", "todo added!").build();
            return ResponseEntity.status(HttpStatus.CREATED).body(jo.toString());
        } else {
            JsonObject jo = Json.createObjectBuilder().add("message", "error").build();
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(jo.toString());
        }
        
    }

    @PutMapping(path="/todos")
    public ResponseEntity<String> editTodo(@RequestBody String body) {
        return ResponseEntity.ok("");
    }
}
