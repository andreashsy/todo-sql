import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Todo } from '../models';
import { TodoService } from '../services/todo.service';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.css']
})
export class TodoFormComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private todoSvc: TodoService,
    private router: Router
    ) {

    }
  users!: String[]
  form!: FormGroup

  ngOnInit(): void {
    this.form = this.createForm()
  }

  addTodo() {
    let todo = this.form.value as Todo
    console.info("Adding todo: ", todo)
    this.todoSvc.addTodo(todo)
      .then(result => {
        console.info(result)
        this.form.reset('')
        this.router.navigate([''])
      })
      .catch(error => {
        console.error(error)
      })
  }

  createForm() {
    return this.fb.group({
      taskname: this.fb.control(''),
      priority: this.fb.control(''),
      duedate: this.fb.control(''),
      username: this.fb.control('')
    })
  }

}
