package space.harbour.countries.domain;

import lombok.Data;
import lombok.ToString;

/**
 * Represents a country with all data I need
 * @author Santiago Faci
 * @version 2021
 */
@Data
@ToString
public class Country {

    private CountryName name;
    private String region;
    private String subregion;
    private int population;

    public String getName() {
        return name.getCommon();
    }
}
