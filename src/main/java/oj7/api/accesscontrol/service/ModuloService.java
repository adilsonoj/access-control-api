package oj7.api.accesscontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import oj7.api.accesscontrol.model.Modulo;
import oj7.api.accesscontrol.repository.ModuloRepository;

@Service
public class ModuloService {
  @Autowired
  ModuloRepository repository;

  public List<Modulo> findAll() {
    return repository.findAll();
  }
}
