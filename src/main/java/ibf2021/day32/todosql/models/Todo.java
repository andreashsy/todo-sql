package ibf2021.day32.todosql.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Todo {
    private int tid;
    private String taskname;
    private String username;
    private String priority;
    private String duedate;

    public void setTid(int tid) {this.tid = tid;}
    public int getTid() {return this.tid;}

    public void setTaskname(String taskname) {this.taskname = taskname;}
    public String getTaskname() {return this.taskname;}

    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return this.username;}

    public void setPriority(String priority) {this.priority = priority;}
    public String getPriority() {return this.priority;}

    public void setDuedate(String duedate) {this.duedate = duedate;}
    public String getDuedate() {return this.duedate;}

    public static Todo populateFromSql(SqlRowSet rs) {
        Todo todo = new Todo();

        todo.setTid(rs.getInt("tid"));
        todo.setTaskname(rs.getString("task_name"));
        todo.setUsername(rs.getString("username"));
        todo.setPriority(rs.getString("priority"));
        todo.setDuedate(rs.getString("due_date"));

        return todo;
    }
    
    public static Todo populateFromJsonString(String jsonString) {
        Todo todo = new Todo();
        try (InputStream is = new ByteArrayInputStream(jsonString.getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();

            todo.setTaskname(data.getString("taskname"));
            todo.setPriority(data.getString("priority"));
            todo.setDuedate(data.getString("duedate"));
            todo.setUsername(data.getString("username"));
           
        } catch (IOException e) {
            System.out.println(e);
        }

        return todo;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("tid", this.getTid())
            .add("taskname", this.getTaskname())
            .add("username", this.getUsername())
            .add("priority", this.getPriority())
            .add("duedate", this.getDuedate())
            .build();
    }
}
