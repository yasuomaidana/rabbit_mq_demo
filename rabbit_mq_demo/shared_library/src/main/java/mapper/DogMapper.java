package mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Dog;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DogMapper {
    ObjectMapper parser = new ObjectMapper();

    public List<Dog> dogsFromMessage(byte[] body){
        try {
            return parser.readValue(body, new TypeReference<List<Dog>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error receiving dogs");
        }
    }
}
