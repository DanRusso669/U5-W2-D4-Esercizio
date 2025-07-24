package danrusso.U5_W2_D3_Esercizio.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import danrusso.U5_W2_D3_Esercizio.entities.Author;
import danrusso.U5_W2_D3_Esercizio.entities.BlogPost;
import danrusso.U5_W2_D3_Esercizio.exceptions.BadRequestException;
import danrusso.U5_W2_D3_Esercizio.exceptions.NotFoundException;
import danrusso.U5_W2_D3_Esercizio.exceptions.UUIDLenghtException;
import danrusso.U5_W2_D3_Esercizio.payloads.NewBlogPostDTO;
import danrusso.U5_W2_D3_Esercizio.repositories.BlogPostRepository;
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
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    private Cloudinary imgUploader;

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

    public BlogPost saveBlogPost(NewBlogPostDTO payload) {
        Author authorFound = this.authorService.findById(payload.authorId());
        BlogPost newBlogPost = new BlogPost(payload.category(), payload.title(), payload.content(), payload.readingTime(), authorFound);
        newBlogPost.setCover("https://picsum.photos/200/300");
        BlogPost savedBP = this.blogPostRepository.save(newBlogPost);
        System.out.println("Blog Post with id " + savedBP.getId() + " saved successfully.");
        return savedBP;
    }

    public BlogPost findByIdAndUpdate(UUID id, NewBlogPostDTO payload) {
        BlogPost found = this.findById(id);
        Author authorFound = this.authorService.findById(payload.authorId());
        if (!found.getAuthor().equals(authorFound)) {
            found.setAuthor(authorFound);
        }

        found.setCategory(payload.category());
        found.setTitle(payload.title());
        found.setContent(payload.content());
        found.setReadingTime(payload.readingTime());

        BlogPost savedBP = this.blogPostRepository.save(found);
        System.out.println("Blog Post with id " + id + " modified successfully.");
        return savedBP;
    }

    public void findByIdAndDelete(UUID id) {
        BlogPost found = this.findById(id);
        this.blogPostRepository.delete(found);
    }

    public String uploadCover(UUID authorId, MultipartFile file) {
        BlogPost found = this.findById(authorId);
        try {
            Map result = imgUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) result.get("url");
            found.setCover(imageUrl);
            this.blogPostRepository.save(found);
            return imageUrl;
        } catch (Exception e) {
            throw new BadRequestException("Something went wrong while saving the cover.");
        }
    }


}
