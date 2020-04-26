package guru.springframework.springmsbeerservice.services;

import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
import guru.springframework.springmsbeerservice.web.mappers.BeerMapper;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import guru.springframework.springmsbeerservice.web.model.BeerPageList;
import guru.springframework.springmsbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author kas
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper mapper;

    @Override
    public BeerDto getById(UUID id) {
        return mapper.toDto(beerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Not found")));
    }

    @Override
    public BeerDto save(BeerDto dto) {
        return mapper.toDto(beerRepository.save(mapper.toEntity(dto)));
    }

    @Override
    public void update(UUID id, BeerDto dto) {
        if (beerRepository.existsById(id)) {
            dto.setId(id);
            beerRepository.save(mapper.toEntity(dto));
        } else {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public BeerPageList getListOfBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest of) {
        Page<Beer> page;

        if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyleEnum)) {
            page = beerRepository.findAllByBeerStyle(beerStyleEnum, of);
        } else if (StringUtils.isEmpty(beerStyleEnum) && !StringUtils.isEmpty(beerName)) {
            page = beerRepository.findAllByBeerName(beerName, of);
        } else if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyleEnum)) {
            page = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyleEnum, of);
        } else {
            page = beerRepository.findAll(of);
        }

        BeerPageList pageList = new BeerPageList(
                page.getContent()
                        .stream().map(mapper::toDto).collect(Collectors.toList()),
                PageRequest.of(
                        page.getPageable().getPageNumber(),
                        page.getPageable().getPageSize()),
                page.getTotalElements());
        return pageList;
    }
}
