package oj7.api.accesscontrol.requests;

import lombok.Builder;
import lombok.Data;
import oj7.api.accesscontrol.model.NivelAcesso;

@Data
@Builder
public class NivelAcessoUsuarioPostRequestBody {
	private String nrCpf01;
	private String nrCpf02;
	private String nrCpf03;
	private String idDvCpf;
  private NivelAcesso nivelAcesso;
	private String cdSupe;
	private String cdDept;
	private String cdDivs;
	private String cdScao;
	private String cdSetr;
}
