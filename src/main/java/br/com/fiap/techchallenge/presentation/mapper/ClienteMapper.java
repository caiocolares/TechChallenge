package br.com.fiap.techchallenge.presentation.mapper;

import br.com.fiap.techchallenge.domain.entity.ClienteEntity;
import br.com.fiap.techchallenge.presentation.dto.response.ClienteResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponse toResponse(ClienteEntity entity);
    List<ClienteResponse> toResponseList(List<ClienteEntity> entities);
}
