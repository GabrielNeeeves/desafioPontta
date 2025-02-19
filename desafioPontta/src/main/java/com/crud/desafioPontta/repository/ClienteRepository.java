package com.crud.desafioPontta.repository;

import com.crud.desafioPontta.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
