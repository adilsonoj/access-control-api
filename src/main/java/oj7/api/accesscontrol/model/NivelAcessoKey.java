package oj7.api.accesscontrol.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class NivelAcessoKey implements Serializable{
  private static final long serialVersionUID = -1L;

  @Column(name = "CD_PROJ")
  private String cdProj;

  @Column(name = "ID_MODU")
  private String idModu;

  @Column(name = "CD_NIVL_ACES")
  private Long cdNivlAces;
}
