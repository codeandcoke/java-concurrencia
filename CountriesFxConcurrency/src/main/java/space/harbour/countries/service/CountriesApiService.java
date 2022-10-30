package space.harbour.countries.service;

import retrofit2.http.Query;
import space.harbour.countries.domain.Country;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * API Interface I need (https://restcountries.com/)
 * @author Santiago Faci
 * @version 2021
 */
public interface CountriesApiService {

    @GET("/v3.1/all")
    Call<List<Country>> getAllCountries();

    // Be carefull because this API returns a country list even if you ask only for one
    @GET("/v3.1/name/{name}")
    Call<List<Country>> getCountry(@Path("name") String name);

    // TODO Add as many operations as you need to call
}
