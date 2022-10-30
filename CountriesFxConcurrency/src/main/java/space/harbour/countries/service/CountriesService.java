package space.harbour.countries.service;

import space.harbour.countries.domain.Country;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import space.harbour.countries.util.Constants;

import java.io.IOException;
import java.util.List;

/**
 * Operations implementation
 * @author Santiago Faci
 * @version 2021
 */
public class CountriesService {

    private CountriesApiService api;

    public CountriesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(CountriesApiService.class);
    }

    public List<Country> getAllCountries() {
        Call<List<Country>> allCountriesCall = api.getAllCountries();
        try {
            Response<List<Country>> response = allCountriesCall.execute();
            return response.body();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

    // TODO Implement the method to get information about a specific country
    public List<Country> getCountries(String searchText) {
        Call<List<Country>> countriesCall = api.getCountry(searchText);
        try {
            Response<List<Country>> response = countriesCall.execute();
            return response.body();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
