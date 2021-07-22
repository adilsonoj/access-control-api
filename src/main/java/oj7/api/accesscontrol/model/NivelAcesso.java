package oj7.api.accesscontrol.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="ADVU_NIVL_ACES")
public class NivelAcesso implements Serializable{
  private static final long serialVersionUID = -1L;

  @EmbeddedId
  private NivelAcessoKey key;

  @OneToOne
  @JoinColumn(name = "CD_NIVL_ACES", referencedColumnName = "CD_NIVL_ACES",  insertable = false, updatable = false)
  private CodigoNivelAcesso codigoNivelAcesso;
}
