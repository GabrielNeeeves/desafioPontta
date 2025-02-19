import { Component } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ClienteService } from '../../services/cliente.service';
import { Observable } from 'rxjs';
import { Cliente } from '../../Cliente.model';

@Component({
  selector: 'app-cliente',
  imports: [FormsModule, CommonModule],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.css'
})
export class ClienteComponent {

  clientes$ = new Observable<Cliente[]>;

  id = '';
  email = '';
  password = '';

  idFind:number = 0;
  emailFind = '';
  passwordFind = '';

  constructor(private service:ClienteService){};

  buttonClick() {

    if(this.id) {
      this.atualizarDados();
      alert("Cliente atualizado!");
      return;
    }

    this.enviarDados();
    alert("Cliente cadastrado!");

  }

  buscarPorId(id:number) {
    this.service.getById(id).subscribe(cliente => {
      this.idFind = cliente.id!;
      this.emailFind = cliente.email;
      this.passwordFind = cliente.password;
    }
      
  );
  }

  atualizarDados() {
    this.service.putCliente({
      id: parseInt(this.id),
      email: this.email,
      password: this.password
    }).subscribe(() => this.buscarDados());
  }

  buscarDados() {
    this.clientes$ = this.service.getCliente();
  }


  enviarDados() {
    this.service.postCliente({
      id: parseInt(this.id),
      email: this.email,
      password: this.password
    }).subscribe(() => this.buscarDados());
  }

  excluirDados(id:number) {
    this.service.deleteCliente(id).subscribe(() => this.buscarDados());
    alert("Cliente excluido!");
  }

  preencherCampos(c:Cliente) {
    this.id = c.id!.toString();
    this.email = c.email;
    this.password = c.password;
  }

}
