package andrey.teplykh.repository.endpoint;

import andrey.teplykh.dto.PersonDto;
import andrey.teplykh.exception.BusinessException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class EndpointService implements MainServiceRepository {

    @Value(value = "${main.host}")
    private String host;

    @Value(value = "${main.path}")
    private String uri;

    @Autowired
    private Gson gson;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<PersonDto> getAllKillers() throws BusinessException {
        final Type listType = new TypeToken<ArrayList<PersonDto>>() {}.getType();
        final String response;
        try {
            response = restTemplate.getForEntity(host + uri, String.class).getBody();
        } catch (final HttpClientErrorException e) {
            throw new BusinessException(e.getStatusCode(), e.getResponseBodyAsString());
        } catch (final ResourceAccessException e) {
            throw new BusinessException(HttpStatus.SERVICE_UNAVAILABLE, "Unable to connect to main service. Please try again later");
        }

        return gson.fromJson(response, listType);
    }

    @Override
    public PersonDto createKiller(PersonDto dto) throws BusinessException {
        final HttpEntity<PersonDto> requestEntity = new HttpEntity<>(dto);
        try {
            final ResponseEntity<String> response = restTemplate.exchange(
                    host + uri + "/create", HttpMethod.POST, requestEntity, String.class);
        } catch (final HttpClientErrorException e) {
            throw new BusinessException(e.getStatusCode(), e.getResponseBodyAsString());
        } catch (final ResourceAccessException e) {
            throw new BusinessException(HttpStatus.SERVICE_UNAVAILABLE, "Unable to connect to main service. Please try again later");
        }
        return null;
    }
}
