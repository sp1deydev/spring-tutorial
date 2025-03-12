package com.gtel.srpingtutorial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Airport {
    @Id
    String iata;
    String name;
    String airportGroupCode;
    String language;
    Integer priority;
}
