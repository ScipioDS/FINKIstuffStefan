package ddd.eshop.ordermanagement.port.client;

import ddd.eshop.producthandler.domain.models.Product;
import lombok.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class ClientProduct {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public ClientProduct(@Value(staticConstructor = "${app.product-handler.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Product> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/product").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
