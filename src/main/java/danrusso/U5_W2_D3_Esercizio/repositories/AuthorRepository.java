package danrusso.U5_W2_D3_Esercizio.repositories;

import danrusso.U5_W2_D3_Esercizio.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByEmail(String email);
}
