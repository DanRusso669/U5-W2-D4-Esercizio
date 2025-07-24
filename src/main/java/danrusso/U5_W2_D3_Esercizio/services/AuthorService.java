package danrusso.U5_W2_D3_Esercizio.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import danrusso.U5_W2_D3_Esercizio.entities.Author;
import danrusso.U5_W2_D3_Esercizio.exceptions.BadRequestException;
import danrusso.U5_W2_D3_Esercizio.exceptions.NotFoundException;
import danrusso.U5_W2_D3_Esercizio.exceptions.UUIDLenghtException;
import danrusso.U5_W2_D3_Esercizio.payloads.NewAuthorDTO;
import danrusso.U5_W2_D3_Esercizio.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private Cloudinary imgUploader;


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

    public Author saveAuthor(NewAuthorDTO payload) {
        this.authorRepository.findByEmail(payload.email()).ifPresent(author -> {
            throw new BadRequestException(payload.email());
        });
        Author newAuthor = new Author(payload.name(), payload.surname(), payload.email(), payload.DOB());
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
        Author savedAuthor = authorRepository.save(newAuthor);
        System.out.println("Author with id " + savedAuthor.getId() + " saved successfully.");
        return savedAuthor;
    }

    public Author findByIdAndUpdate(UUID id, NewAuthorDTO payload) {
        Author found = this.findById(id);

        if (!found.getEmail().equals(payload.email())) {
            this.authorRepository.findByEmail(payload.email()).ifPresent(author -> {
                throw new BadRequestException(payload.email());
            });
        }

        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        found.setDOB(payload.DOB());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());

        Author modifiedAuthor = authorRepository.save(found);

        System.out.println("Author with " + found.getId() + " modified successfully.");

        return modifiedAuthor;
    }

    public void findByIdAndDelete(UUID id) {
        Author found = this.findById(id);
        this.authorRepository.delete(found);
    }

    public String uploadAvatar(UUID authorId, MultipartFile file) {
        Author found = this.findById(authorId);
        try {
            Map result = imgUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("url");
            found.setAvatar(imageUrl);
            this.authorRepository.save(found);
            return imageUrl;
        } catch (Exception e) {
            throw new BadRequestException("Something went wrong while saving the image.");
        }
    }
}
