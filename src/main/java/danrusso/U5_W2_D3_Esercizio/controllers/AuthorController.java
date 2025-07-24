package danrusso.U5_W2_D3_Esercizio.controllers;

import danrusso.U5_W2_D3_Esercizio.entities.Author;
import danrusso.U5_W2_D3_Esercizio.exceptions.ValidationException;
import danrusso.U5_W2_D3_Esercizio.payloads.NewAuthorDTO;
import danrusso.U5_W2_D3_Esercizio.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> findall(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "name") String sortBy) {
        return this.authorService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody @Validated NewAuthorDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.authorService.saveAuthor(payload);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable UUID authorId) {
        return this.authorService.findById(authorId);
    }

    @PutMapping("/{authorId}")
    public Author findByIdAndUpdate(@PathVariable UUID authorId, @RequestBody @Validated NewAuthorDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.authorService.findByIdAndUpdate(authorId, payload);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID authorId) {
        this.authorService.findByIdAndDelete(authorId);
    }

    @PatchMapping("/{authorId}/avatar")
    public String uploadAvatar(@PathVariable UUID authorId, @RequestParam("avatar") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        return this.authorService.uploadAvatar(file);
    }

}
