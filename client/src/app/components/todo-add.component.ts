import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Todo } from '../models';
import { TodoService } from '../services/todo.service';
import { TodoFormComponent } from './todo-form.component';

@Component({
  selector: 'app-todo-add',
  templateUrl: './todo-add.component.html',
  styleUrls: ['./todo-add.component.css']
})
export class TodoAddComponent implements OnInit {
  @ViewChild(TodoFormComponent)
  todoFormComponent!: TodoFormComponent


  constructor(
    private todoSvc: TodoService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  addTodo() {
    let todo = this.todoFormComponent.getTodo()
    console.info("Adding todo: ", todo)
    this.todoSvc.addTodo(todo)
      .then(result => {
        console.info(result)
        this.todoFormComponent.resetForm()
        this.router.navigate([''])
      })
      .catch(error => {
        console.error(error)
      })
  }

}
