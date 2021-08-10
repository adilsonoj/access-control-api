package oj7.api.accesscontrol.model;
import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "CD_NIVL_ACES", referencedColumnName = "CD_NIVL_ACES",  insertable = false, updatable = false)
  private CodigoNivelAcesso codigoNivelAcesso;
}
