package ibf2021.day32.todosql.repositories;

public class SQL {
    public static final String SQL_GET_ALL_TODOS = "select * from tasks";
    public static final String SQL_ADD_TODO = "insert into tasks (username, task_name, priority, due_date) values (?, ?, ?, ?)";
    public static final String SQL_CHECK_IF_USER_EXISTS = "select count(*) as count from user where username=?";
}
