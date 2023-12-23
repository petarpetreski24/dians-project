package mk.ukim.finki.vinodventuraapp.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WineryParser {


    private final ResourceLoader resourceLoader;

    public WineryParser(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Winery> parseJsonFile(String filePath) {
        List<Winery> wineries = new ArrayList<>();

        try {
            Resource resource = resourceLoader.getResource("classpath:" + filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(resource.getFile());

            for (JsonNode wineryNode : rootNode) {
                Winery winery = parseWinery(wineryNode);
                wineries.add(winery);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return wineries;
    }

    private Winery parseWinery(JsonNode wineryNode) {
        String name = wineryNode.get("Name").asText();
        String address = wineryNode.get("Address").asText();
        String location = wineryNode.get("Location").asText();
        String mobNumber = wineryNode.get("Mob. Number").asText();
        String occupations = wineryNode.get("Occupation").asText();
        String workingHours = wineryNode.get("WorkingHours").asText();
        String website = wineryNode.get("Website").asText();
        Double latitude = wineryNode.get("Latitude").asDouble();
        Double longitude = wineryNode.get("Longitude").asDouble();

        return new Winery(name, address, location, mobNumber, occupations, workingHours, website, latitude, longitude);
    }
}
