package space.harbour.thezoo.task;

import javafx.concurrent.Task;
import space.harbour.thezoo.domain.Animal;
import space.harbour.thezoo.service.AnimalsService;

import java.util.List;

/**
 * A background task to search countries
 */
public class RemoveAnimalTask extends Task<List<Animal>> {

    private long animalId;

    public RemoveAnimalTask(long animalId) {
        this.animalId = animalId;
    }
    
    @Override
    protected Animal call() throws Exception {
        AnimalsService animalsService = new AnimalsService();
        return animalsService.removeAnimal(animalId);

    }
}
