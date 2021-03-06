package oj7.api.accesscontrol.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oj7.api.accesscontrol.model.NivelAcesso;
import oj7.api.accesscontrol.repository.NivelAcessoRepository;

@Service
public class NivelAcessoService {

  @Autowired
  NivelAcessoRepository naRepository;

  public List<NivelAcesso> findByCdProjIdModu(String cdProj, String idModu){
    return naRepository.getByCdProjIdModu(cdProj, idModu);
  }

  public Page<NivelAcesso> findAll(Pageable pageable) {
    return naRepository.findAll(pageable);
  }
  
}
