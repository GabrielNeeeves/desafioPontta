package com.crud.desafioPontta;

import com.crud.desafioPontta.controller.ClienteController;
import com.crud.desafioPontta.model.ClienteDto;
import com.crud.desafioPontta.model.ClienteModel;
import com.crud.desafioPontta.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClienteControllerTesteIntegracao {

    @Autowired
    private ClienteController controller;

    @Autowired
    private ClienteRepository repo;

    @Test
    @DisplayName("Verifica email do ultimo cliente cadastrado")
    public void retornaTrueSeEmailDaUltimaInsercaoCondizComOPost() throws Exception {

        var dto = new ClienteDto("kk@gmail.com", "222");
        //var cliente = new ClienteModel(dto);
        controller.postCliente(dto);

        List<ClienteModel> clientes = repo.findAll();

        ClienteModel ultimaInsercao = clientes.get(clientes.size()-1);

        Assertions.assertEquals("kk@gmail.com", ultimaInsercao.getEmail());

    }

}
