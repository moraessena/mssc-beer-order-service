package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.beer.BeerServiceClientImpl;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {
    @Autowired
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    private BeerServiceClientImpl beerServiceClient;

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto orderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        beerServiceClient.getBeerByUPC(line.getUpc())
                .ifPresent(beer -> {
                    orderLineDto.setBeerId(beer.getId());
                    orderLineDto.setBeerName(beer.getBeerName());
                    orderLineDto.setBeerStyle(beer.getBeerStyle());
                    orderLineDto.setPrice(beer.getPrice());
                    orderLineDto.setUpc(beer.getUpc());
                });
        return orderLineDto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        BeerOrderLine beerOrderLine = beerOrderLineMapper.dtoToBeerOrderLine(dto);
        beerServiceClient.getBeerByUPC(dto.getUpc()).ifPresent(beer -> {
            beerOrderLine.setBeerId(beer.getId());
            beerOrderLine.setUpc(beer.getUpc());
        });
        return beerOrderLine;
    }
}
