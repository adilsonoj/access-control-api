package oj7.api.accesscontrol.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

  public String getOi(){
    return (this.key.getCdSupe()+
            this.key.getCdDept()+
            this.key.getCdDivs()+
            this.key.getCdScao()+
            this.key.getCdSetr()).trim();
  }
}
