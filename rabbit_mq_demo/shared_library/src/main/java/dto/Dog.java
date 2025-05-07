package dto;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class Dog {
    private String name;
    private String race;
    private byte age;
}
