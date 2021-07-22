package oj7.api.accesscontrol.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="ADVU_NIVL_ACES_USUA")
public class NivelAcessoUsuario implements Serializable{
  
  private static final long serialVersionUID = -1L;

  @EmbeddedId
  private NivelAcessoUsuarioKey key;
}
