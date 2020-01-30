package demo.alex.repository;

import demo.alex.data.Salt;
import org.springframework.data.repository.CrudRepository;

public interface SaltRepository extends CrudRepository<Salt, Long> {
}
