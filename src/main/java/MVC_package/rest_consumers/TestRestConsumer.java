package MVC_package.rest_consumers;

import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
/**
 * By annotating TestRestConsumer with
 * @RestController, you declare that it should be automatically discovered by Spring component
 * scanning and instantiated as a bean in the Spring application context.
 **/
public class TestRestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    //Todo: NOT WORKING YET
    public User getJsonUser () {
        User result = restTemplate.getForObject("http://localhost:8080/testREST/getJsonUser", User.class);
        // restTemplate.patchForObject()
        return result;
    }

    // Testing GET Request
    public WordTranslate getWords () {
        WordTranslate result = restTemplate.getForObject("http://localhost:8080/testREST/getAnyWord", WordTranslate.class);
        return result;
    }

    // Testing POST Request
    public long createWord(String ukrWord, String engWord, Integer point){

        WordTranslate wordTranslate = new WordTranslate();
        wordTranslate.setPoints(point);
        wordTranslate.setEngword(engWord);
        wordTranslate.setUkrword(ukrWord);

        long id = restTemplate.postForObject("http://localhost:8080/testREST/createWord",wordTranslate,Long.class);
        return id;
    }
}