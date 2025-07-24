package danrusso.U5_W2_D3_Esercizio.services;

import danrusso.U5_W2_D3_Esercizio.entities.Author;
import danrusso.U5_W2_D3_Esercizio.exceptions.BadRequestException;
import danrusso.U5_W2_D3_Esercizio.exceptions.NotFoundException;
import danrusso.U5_W2_D3_Esercizio.exceptions.UUIDLenghtException;
import danrusso.U5_W2_D3_Esercizio.payloads.NewAuthorPayload;
import danrusso.U5_W2_D3_Esercizio.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public Page<Author> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.authorRepository.findAll(pageable);
    }

    public Author findById(UUID id) {
        if (id.toString().length() != 36)
            throw new UUIDLenghtException("The UUID entered is either too short or too long.");
        return this.authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "Author"));
    }

    public Author saveAuthor(NewAuthorPayload payload) {
        this.authorRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
            throw new BadRequestException(payload.getEmail());
        });
        Author newAuthor = new Author(payload.getName(), payload.getSurname(), payload.getEmail(), payload.getDOB());
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());
        Author savedAuthor = authorRepository.save(newAuthor);
        System.out.println("Author with id " + savedAuthor.getId() + " saved successfully.");
        return savedAuthor;
    }

    public Author findByIdAndUpdate(UUID id, NewAuthorPayload payload) {
        Author found = this.findById(id);

        if (!found.getEmail().equals(payload.getEmail())) {
            this.authorRepository.findByEmail(payload.getEmail()).ifPresent(author -> {
                throw new BadRequestException(payload.getEmail());
            });
        }

        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        found.setDOB(payload.getDOB());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());

        Author modifiedAuthor = authorRepository.save(found);

        System.out.println("Author with " + found.getId() + " modified successfully.");

        return modifiedAuthor;
    }

    public void findByIdAndDelete(UUID id) {
        Author found = this.findById(id);
        this.authorRepository.delete(found);
    }
}
