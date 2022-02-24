package ibf2021.day32.todosql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2021.day32.todosql.repositories.TodoRepository;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepo;

    
}
