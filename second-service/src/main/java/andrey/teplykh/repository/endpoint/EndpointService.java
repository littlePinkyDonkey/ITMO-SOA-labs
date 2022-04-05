package andrey.teplykh.repository.endpoint;

import andrey.teplykh.dto.PersonDto;
import andrey.teplykh.exception.BusinessException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.corn.converter.json.JsTypeComplex;
import net.sf.corn.converter.json.JsonStringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class EndpointService implements MainServiceRepository {

    private String host;

    @Value(value = "${main.path}")
    private String uri;

    @Value(value = "${sd.host}")
    private String sdHost;

    @Value(value = "${sd.path}")
    private String sdPath;

    @Autowired
    private Gson gson;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<PersonDto> getAllKillers() throws BusinessException {
        findService();
        final Type listType = new TypeToken<ArrayList<PersonDto>>() {}.getType();
        final String response;
        try {
            response = restTemplate.getForEntity(host + uri, String.class).getBody();
        } catch (final HttpClientErrorException e) {
            throw new BusinessException(e.getStatusCode(), e.getResponseBodyAsString());
        } catch (final ResourceAccessException e) {
            throw new BusinessException(HttpStatus.SERVICE_UNAVAILABLE, "Unable to connect to main andrei.teplykh.service. Please try again later");
        }

        return gson.fromJson(response, listType);
    }

    @Override
    public PersonDto createKiller(PersonDto dto) throws BusinessException {
        findService();
        final HttpEntity<PersonDto> requestEntity = new HttpEntity<>(dto);
        try {
            final ResponseEntity<String> response = restTemplate.exchange(
                    host + uri + "/create", HttpMethod.POST, requestEntity, String.class);
        } catch (final HttpClientErrorException e) {
            throw new BusinessException(e.getStatusCode(), e.getResponseBodyAsString());
        } catch (final ResourceAccessException e) {
            throw new BusinessException(HttpStatus.SERVICE_UNAVAILABLE, "Unable to connect to main andrei.teplykh.service. Please try again later");
        }
        return null;
    }

    private void findService() throws BusinessException {
        final String url = sdHost + sdPath;
        try {
            final String response = restTemplate.getForEntity(url, String.class).getBody();
            final JsTypeComplex jsonResponse = (JsTypeComplex) JsonStringParser.parseJsonString(response);
            host = String.format("https://%s:%s/",
                    jsonResponse.get("Address").toString().replace("\"", ""),
                    jsonResponse.get("Port").toString());
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Consul error");
        }
    }
}
