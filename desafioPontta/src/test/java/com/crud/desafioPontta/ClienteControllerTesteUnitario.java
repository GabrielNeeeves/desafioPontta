package com.crud.desafioPontta;

import com.crud.desafioPontta.controller.ClienteController;
import com.crud.desafioPontta.model.ClienteDto;
import com.crud.desafioPontta.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTesteUnitario {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteRepository repo;

    @Test
    @DisplayName("Deve lançar Exception se cliente não tiver @ no email")
    public void lancaExcecaoSeClienteNaoTemArroba() throws Exception {

        var dto = new ClienteDto("testeemail.com", "999999");

        Assertions.assertThrows(Exception.class, () -> controller.postCliente(dto));
    }

}
