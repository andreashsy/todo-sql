import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Todo } from "../models";

@Injectable()
export class TodoService{
  constructor(private http: HttpClient) {}

  async getAllTodos(): Promise<Todo[]> {
    return await lastValueFrom(
      this.http.get<Todo[]>('/api/todos')
    )
  }

  async addTodo(todo: Todo) {
    return await lastValueFrom(
      this.http.post('/api/todos', todo)
    )
  }
}
