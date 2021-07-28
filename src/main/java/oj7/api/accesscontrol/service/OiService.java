package oj7.api.accesscontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oj7.api.accesscontrol.model.Oi;
import oj7.api.accesscontrol.repository.OiRepository;

@Service
public class OiService {
  
  @Autowired
  OiRepository repository;

  public List<Oi> findLikeOi (String oi){
    return repository.findLikeOi(oi.toUpperCase());
  }
}
