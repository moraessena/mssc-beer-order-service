package guru.sfg.beer.order.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class BeerClientConfiguration {

    public static final String BEER_CLIENT_BEAN = "beer_client_bean";

    @Value("${kkr.beer-service.host}")
    private String apiHost;

    @Bean(name = BEER_CLIENT_BEAN)
    public RestTemplate apiClient(RestTemplateBuilder builder) {
        return builder.rootUri(apiHost)
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(30))
                .build();
    }


}
