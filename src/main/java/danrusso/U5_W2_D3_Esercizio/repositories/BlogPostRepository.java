package danrusso.U5_W2_D3_Esercizio.repositories;

import danrusso.U5_W2_D3_Esercizio.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {
}
