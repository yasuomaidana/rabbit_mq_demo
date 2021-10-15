package dto;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class DogDto {
    private String name;
    private String race;
    private byte age;
}
