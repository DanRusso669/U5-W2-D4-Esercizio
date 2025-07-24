package danrusso.U5_W2_D3_Esercizio.controllers;

import danrusso.U5_W2_D3_Esercizio.entities.BlogPost;
import danrusso.U5_W2_D3_Esercizio.payloads.NewBlogPostPayload;
import danrusso.U5_W2_D3_Esercizio.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public BlogPost createBlogPost(@RequestBody NewBlogPostPayload payload) {
        return this.blogPostService.saveBlogPost(payload);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable UUID blogPostId) {
        return this.blogPostService.findById(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    public BlogPost findByIdAndUpdate(@PathVariable UUID blogPostId, @RequestBody NewBlogPostPayload payload) {
        return this.blogPostService.findByIdAndUpdate(blogPostId, payload);
    }

    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID blogPostId) {
        this.blogPostService.findByIdAndDelete(blogPostId);
    }
}
