package guru.springframework.springmsbeerservice.web.mappers;

import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * @author kas
 */
@Mapper(uses = DateMapper.class)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto toDto(Beer beer);
    BeerDto toDtoWithInventory(Beer beer);
    Beer toEntity(BeerDto dto);
}
