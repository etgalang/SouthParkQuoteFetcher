import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SouthParkQuoteFetcher {

    
    
     public static void main(String[] args) {
    SpringApplication.run(SouthParkQuoteFetcher.class, args);
    getSouthParkQuotes();
}


    // Method to fetch South Park quotes and print them
    public static void getSouthParkQuotes() {
        try {
            String url = "https://southparkquotes.onrender.com/v1/quotes";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            
            String quotesData = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(quotesData);

          
                String quote =  root.findValue("quote").asText();
                String character =  root.findValue("character").asText();

                System.out.println("**********South Park Quote!********");
                System.out.println("Quote: " + quote);
                System.out.println("Character: " + character);
                System.out.println();
         } catch (JsonProcessingException ex) {
            Logger.getLogger(RestApiApplication.class.getName()).log(
                    Level.SEVERE,
                    null, ex);
            System.out.println("error in getCatFact");

        }
    }
}