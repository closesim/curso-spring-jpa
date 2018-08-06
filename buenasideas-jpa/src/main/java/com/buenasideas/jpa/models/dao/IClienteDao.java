package com.buenasideas.jpa.models.dao;

import com.buenasideas.jpa.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
