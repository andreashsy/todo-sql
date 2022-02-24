import { Component, OnInit } from '@angular/core';
import { Todo } from '../models';
import { TodoService } from '../services/todo.service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  constructor(private todoSvc: TodoService) { }
  todos!: Todo[]
  ngOnInit(): void {
    this.todoSvc.getAllTodos()
      .then(result => {
        this.todos = result
      })
      .catch(error => {
        console.error(error)
        this.todos = []
      })
  }

}
