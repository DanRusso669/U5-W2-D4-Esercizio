package danrusso.U5_W2_D3_Esercizio.services;

import danrusso.U5_W2_D3_Esercizio.entities.Author;
import danrusso.U5_W2_D3_Esercizio.entities.BlogPost;
import danrusso.U5_W2_D3_Esercizio.exceptions.NotFoundException;
import danrusso.U5_W2_D3_Esercizio.exceptions.UUIDLenghtException;
import danrusso.U5_W2_D3_Esercizio.payloads.NewBlogPostPayload;
import danrusso.U5_W2_D3_Esercizio.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorService authorService;

    public Page<BlogPost> findAll(int pageNum, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 10;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return this.blogPostRepository.findAll(pageable);
    }

    public BlogPost findById(UUID id) {
        if (id.toString().length() != 36)
            throw new UUIDLenghtException("The UUID entered is either too short or too long.");
        return this.blogPostRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "Blog Post"));
    }

    public BlogPost saveBlogPost(NewBlogPostPayload payload) {
        Author authorFound = this.authorService.findById(payload.getAuthorId());
        BlogPost newBlogPost = new BlogPost(payload.getCategory(), payload.getTitle(), payload.getContent(), payload.getReadingTime(), authorFound);
        newBlogPost.setCover("https://picsum.photos/200/300");
        BlogPost savedBP = this.blogPostRepository.save(newBlogPost);
        System.out.println("Blog Post with id " + savedBP.getId() + " saved successfully.");
        return savedBP;
    }

    public BlogPost findByIdAndUpdate(UUID id, NewBlogPostPayload payload) {
        BlogPost found = this.findById(id);
        Author authorFound = this.authorService.findById(payload.getAuthorId());
        if (!found.getAuthor().equals(authorFound)) {
            found.setAuthor(authorFound);
        }

        found.setCategory(payload.getCategory());
        found.setTitle(payload.getTitle());
        found.setContent(payload.getContent());
        found.setReadingTime(payload.getReadingTime());

        BlogPost savedBP = this.blogPostRepository.save(found);
        System.out.println("Blog Post with id " + id + " modified successfully.");
        return savedBP;
    }

    public void findByIdAndDelete(UUID id) {
        BlogPost found = this.findById(id);
        this.blogPostRepository.delete(found);
    }


}
