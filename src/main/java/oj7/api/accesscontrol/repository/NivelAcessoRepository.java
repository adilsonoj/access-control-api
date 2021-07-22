package oj7.api.accesscontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import oj7.api.accesscontrol.model.NivelAcesso;
import oj7.api.accesscontrol.model.NivelAcessoKey;

@Repository
public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, NivelAcessoKey>{

  @Query("select n from NivelAcesso n where n.key.cdProj = ?1 and n.key.idModu = ?2")
  public List<NivelAcesso> getByCdProjIdModu(String cdProj, String idModu);
}
