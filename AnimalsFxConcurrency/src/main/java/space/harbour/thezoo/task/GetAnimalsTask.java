package space.harbour.thezoo.task;

import javafx.concurrent.Task;
import space.harbour.thezoo.domain.Animal;
import space.harbour.thezoo.service.AnimalsService;

import java.util.List;

/**
 * A background task to search countries
 */
public class GetAnimalsTask extends Task<List<Animal>> {
    @Override
    protected List<Animal> call() throws Exception {
        AnimalsService animalsService = new AnimalsService();
        return animalsService.getAllAnimals();
    }
}
