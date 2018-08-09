package com.buenasideas.jpa.models.dao;

import com.buenasideas.jpa.models.entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}
