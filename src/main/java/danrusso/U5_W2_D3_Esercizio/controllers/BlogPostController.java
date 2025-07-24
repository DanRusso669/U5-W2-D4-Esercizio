package danrusso.U5_W2_D3_Esercizio.controllers;

import danrusso.U5_W2_D3_Esercizio.entities.BlogPost;
import danrusso.U5_W2_D3_Esercizio.exceptions.ValidationException;
import danrusso.U5_W2_D3_Esercizio.payloads.NewBlogPostDTO;
import danrusso.U5_W2_D3_Esercizio.payloads.NewBlogPostRespDTO;
import danrusso.U5_W2_D3_Esercizio.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "title") String sortBy) {
        return this.blogPostService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewBlogPostRespDTO createBlogPost(@RequestBody @Validated NewBlogPostDTO payload, BindingResult validationResults) {
        if (validationResults.hasErrors()) {
            throw new ValidationException(validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        BlogPost newBlogPost = this.blogPostService.saveBlogPost(payload);
        return new NewBlogPostRespDTO(newBlogPost.getCategory(), newBlogPost.getTitle(), newBlogPost.getContent(), newBlogPost.getReadingTime(), newBlogPost.getAuthor());
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable UUID blogPostId) {
        return this.blogPostService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    public BlogPost findByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody @Validated NewBlogPostDTO payload, BindingResult validationResults) {
        if (validationResults.hasErrors()) {
            throw new ValidationException(validationResults.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.blogPostService.findByIdAndUpdate(blogPostId, payload);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID blogPostId) {
        this.blogPostService.findByIdAndDelete(blogPostId);
    }
}
