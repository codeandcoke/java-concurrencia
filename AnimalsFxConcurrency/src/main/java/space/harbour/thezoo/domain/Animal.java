package space.harbour.thezoo.domain;

import lombok.Data;
import lombok.ToString;

/**
 * Represents a country with all data I need
 * @author Santiago Faci
 * @version 2021
 */
@Data
@ToString
public class Animal {

    private long id;
    private String name;
    private String race;
    private float weight;
    private int maxSpeed;
}
