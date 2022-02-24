import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Todo } from '../models';
import { TodoService } from '../services/todo.service';

@Component({
  selector: 'app-todo-add',
  templateUrl: './todo-add.component.html',
  styleUrls: ['./todo-add.component.css']
})
export class TodoAddComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private todoSvc: TodoService,
    private router: Router
    ) {

    }
  users!: String[]
  addForm!: FormGroup

  ngOnInit(): void {
    this.addForm = this.createForm()
  }

  addTodo() {
    let todo = this.addForm.value as Todo
    console.info("Adding todo: ", todo)
    this.todoSvc.addTodo(todo)
      .then(result => {
        console.info(result)
        this.addForm.reset('')
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
