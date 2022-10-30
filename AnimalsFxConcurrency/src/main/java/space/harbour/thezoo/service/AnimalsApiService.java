package space.harbour.thezoo.service;

import retrofit2.http.DELETE;
import space.harbour.thezoo.domain.Animal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * API Interface I need (https://restcountries.com/)
 * @author Santiago Faci
 * @version 2021
 */
public interface AnimalsApiService {

    @GET("animals")
    Call<List<Animal>> getAllAnimals();

    // Be carefull because this API returns a country list even if you ask only for one
    @GET("animals/{animalId}")
    Call<Animal> getAnimal(@Path("animalId") long animalId);

    @DELETE("animals/{animalId}")
    Call<Void> removeAnimal(@Path("animalId") long animalId);
}
