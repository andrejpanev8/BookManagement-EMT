package mk.ukim.finki.emtlab1.repository;

import mk.ukim.finki.emtlab1.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
