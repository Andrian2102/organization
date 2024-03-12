package organization.structural.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import organization.structural.entity.Person;
import organization.structural.pojo.PersonOutput;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query(value = "SELECT id, nama, atasan FROM public.person where atasan=:id ORDER BY id Desc", nativeQuery = true)
    List<Person> findBawahan(UUID id);
}
