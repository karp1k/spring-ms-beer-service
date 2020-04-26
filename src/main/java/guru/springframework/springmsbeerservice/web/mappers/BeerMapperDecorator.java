package guru.springframework.springmsbeerservice.web.mappers;

import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.services.BeerInventoryService;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Doubtful decision. Perhaps a call to the {@link BeerInventoryService} needs
 * to be moved to {@link guru.springframework.springmsbeerservice.services.BeerService} impl
 * instead of mixing logic in decorator
 * @author kas
 */
public abstract class BeerMapperDecorator implements BeerMapper {

    private BeerMapper mapper;
    private BeerInventoryService inventoryService;

    @Override
    public BeerDto toDto(Beer beer) {
        return mapper.toDto(beer);
    }

    @Override
    public BeerDto toDtoWithInventory(Beer beer) {
        Integer quantityOnHand = inventoryService.getOnHandInventory(beer.getId());
        BeerDto dto = mapper.toDto(beer);
        dto.setQuantityOnHand(quantityOnHand);
        return dto;
    }

    @Override
    public Beer toEntity(BeerDto dto) {
        return mapper.toEntity(dto);
    }

    // setter injection because of MapStruct generation impl of BeerMapper
    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    // setter injection because of MapStruct generation impl of BeerMapper
    @Autowired
    public void setInventoryService(BeerInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
}
