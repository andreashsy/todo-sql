import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
    private todoSvc: TodoService
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
