package oj7.api.accesscontrol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="EEVU_AM")
@IdClass(OiKey.class)
public class Oi {
  @Id
  @Column(name = "cd_supe")
	private String cdSupe;
	@Id
	@Column(name = "cd_dept")
	private String cdDept;
	@Id
	@Column(name = "cd_divs")
	private String cdDivs;
	@Id
	@Column(name = "cd_scao")
	private String cdScao;
	@Id
	@Column(name = "cd_setr")
	private String cdSetr;

  public String getOi(){
    return (this.cdSupe+this.cdDept+this.cdDivs+this.cdScao+this.cdSetr).trim();
  }
}
