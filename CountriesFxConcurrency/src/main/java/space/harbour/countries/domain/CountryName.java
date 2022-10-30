package space.harbour.countries.domain;

import lombok.Data;
import lombok.ToString;

/**
 * Represents a country name (see a sample at https://restcountries.com/v3.1/all)
 * @author Santiago Faci
 * @version 2021
 */
@Data
@ToString
public class CountryName {

    private String common;
    private String official;
}
