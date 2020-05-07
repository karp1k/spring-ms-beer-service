package guru.springframework.springmsbeerservice.events;

import guru.springframework.springmsbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @author kas
 */
@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -3996810961306332731L;

    private final BeerDto beerDto;
}
