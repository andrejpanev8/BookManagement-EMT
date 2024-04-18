package mk.ukim.finki.emtlab1.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emtlab1.model.Country;
import mk.ukim.finki.emtlab1.repository.CountryRepository;
import mk.ukim.finki.emtlab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    CountryRepository countryRepository;
    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);
        return Optional.of(countryRepository.save(country));
    }

    @Transactional
    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = countryRepository.findById(id).orElseThrow(NoSuchElementException::new);

        country.setName(name);
        country.setContinent(continent);

        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
