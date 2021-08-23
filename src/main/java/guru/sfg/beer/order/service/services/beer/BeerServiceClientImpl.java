package guru.sfg.beer.order.service.services.beer;

import guru.sfg.beer.order.service.config.BeerClientConfiguration;
import guru.sfg.beer.order.service.web.model.beer.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceClientImpl implements BeerService {

    private String beerPath = "/api/v1/beer";

    @Autowired
    @Qualifier(BeerClientConfiguration.BEER_CLIENT_BEAN)
    private RestTemplate apiClient;

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        try {
            BeerDto response = apiClient.getForObject(beerPath.concat("/{id}"), BeerDto.class, id.toString());
            return Optional.ofNullable(response);
        } catch (Exception e) {
            log.error("Failed to get beer {}: {}", id.toString(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<BeerDto> getBeerByUPC(String upc) {
        try {
            BeerDto response = apiClient.getForObject(beerPath.concat("?upc={upc}"), BeerDto.class, upc);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            log.error("Failed to get beer {}: {}", upc, e);
            return Optional.empty();
        }
    }

}
