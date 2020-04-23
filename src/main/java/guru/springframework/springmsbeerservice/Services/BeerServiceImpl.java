package guru.springframework.springmsbeerservice.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springmsbeerservice.domain.Beer;
import guru.springframework.springmsbeerservice.repositories.BeerRepository;
import guru.springframework.springmsbeerservice.web.mappers.BeerMapper;
import guru.springframework.springmsbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
}
