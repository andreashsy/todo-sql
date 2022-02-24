package ibf2021.day32.todosql.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2021.day32.todosql.models.Todo;
import static ibf2021.day32.todosql.repositories.SQL.*;

@Repository
public class TodoRepository {
    @Autowired
    JdbcTemplate template;

    public List<Todo> getAllTodos() {
        List<Todo> todos = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_TODOS);
        while (rs.next()) {
            final Todo todo = Todo.populateFromSql(rs);
            todos.add(todo);
        }
        return todos;
    }

    public boolean addTodo(Todo todo) {
        final int added = template.update(
            SQL_ADD_TODO, 
            todo.getUsername(), 
            todo.getTaskname(), 
            todo.getPriority(), 
            todo.getDuedate());

        return added > 0;
    }

    public boolean doesUserExist(String user) {
        int count = 0;
        final SqlRowSet rs = template.queryForRowSet(SQL_CHECK_IF_USER_EXISTS, user);
        while (rs.next()) {
            count = rs.getInt("count");
        }
        return count == 1;
    }
}
