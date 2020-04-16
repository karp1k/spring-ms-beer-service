package guru.springframework.springmsbeerservice.web.mappers;

import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * @author kas
 */
@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto toDto(Beer beer);
    Beer toEntity(BeerDto dto);
}
