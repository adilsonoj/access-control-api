package oj7.api.accesscontrol.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import oj7.api.accesscontrol.model.Pessoa;
import oj7.api.accesscontrol.requests.PessoaPostRequestBody;
import oj7.api.accesscontrol.requests.PessoaPutRequestBody;


@Mapper(componentModel="spring")
public abstract class PessoaMapper {
  public static final PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);
  public abstract Pessoa toPessoa(PessoaPostRequestBody pessoaPostRequestBody);
  public abstract Pessoa toPessoa(PessoaPutRequestBody pessoaPutRequestBody);
}
