package oj7.api.accesscontrol.requests;

import lombok.Data;

@Data
public class PessoaPostRequestBody {
  private Long nrNip;
  private String cdNind;
}
