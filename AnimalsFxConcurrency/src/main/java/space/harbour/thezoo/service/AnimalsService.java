package space.harbour.thezoo.service;

import space.harbour.thezoo.domain.Animal;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import space.harbour.thezoo.util.Constants;

import java.io.IOException;
import java.util.List;

/**
 * Operations implementation
 * @author Santiago Faci
 * @version 2021
 */
public class AnimalsService {

    private AnimalsApiService api;

    public AnimalsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(AnimalsApiService.class);
    }

    public List<Animal> getAllAnimals() {
        Call<List<Animal>> allAnimalsCall = api.getAllAnimals();

        try {
            System.out.println("calling API");
            Response<List<Animal>> response = allAnimalsCall.execute();
            System.out.println(response.body().toString());
            return response.body();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

    public void removeAnimal(long animalId) throws IOException {
        Call<Void> animalsCall = api.removeAnimal(animalId);
        animalsCall.execute();
    }
}
