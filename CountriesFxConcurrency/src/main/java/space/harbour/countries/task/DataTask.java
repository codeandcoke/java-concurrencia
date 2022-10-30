package space.harbour.countries.task;

import javafx.concurrent.Task;
import space.harbour.countries.domain.Country;
import space.harbour.countries.service.CountriesService;

import java.util.List;

/**
 * A background task to search countries
 */
public class DataTask extends Task<List<Country>> {

    private String searchText;

    public DataTask() {
    }

    public DataTask(String searchText) {
        this.searchText = searchText;
    }

    @Override
    protected List<Country> call() throws Exception {
        CountriesService countriesService = new CountriesService();
        if (searchText == null) {
            return countriesService.getAllCountries();
        } else {
            return countriesService.getCountries(searchText);
        }
    }
}
