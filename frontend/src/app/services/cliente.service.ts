import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Cliente } from '../Cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private url = 'http://localhost:8080/clientes';

  constructor(private http:HttpClient) { }

  //GET
  getCliente() {
    return this.http.get<Cliente[]>(this.url);
  }

  //POST
  postCliente(cliente:Cliente) {
    return this.http.post<Cliente>(this.url, cliente);
  }

  //DELETE
  deleteCliente(id:number) {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  //PUT
  putCliente(cliente:Cliente) {
    return this.http.put<Cliente>(`${this.url}/${cliente.id}`, {
      email: cliente.email,
      password: cliente.password
    });
  }

}
