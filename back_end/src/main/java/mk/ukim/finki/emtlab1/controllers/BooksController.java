package mk.ukim.finki.emtlab1.controllers;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emtlab1.model.Author;
import mk.ukim.finki.emtlab1.model.DTOs.KnigaDTO;
import mk.ukim.finki.emtlab1.model.Kniga;
import mk.ukim.finki.emtlab1.model.enumerations.CategoryType;
import mk.ukim.finki.emtlab1.service.AuthorService;
import mk.ukim.finki.emtlab1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping({"/api/books", "/api/"})
public class BooksController {
    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping
    public List<Kniga> listAllBooks(){
        return bookService.findAll();
    }
    @GetMapping("/authors")
    public List<Author> listAllAuthors(){
        return authorService.findALl();
    }
    @GetMapping("/categories")
    public List<String> listAllCategories(){
        return Arrays.stream(CategoryType.values()).map(Enum::name).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kniga> findById(@PathVariable Long id){
        return bookService.findById(id).map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Kniga> createBook(@RequestBody KnigaDTO knigaDTO){
        return bookService.save(knigaDTO).map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Kniga> editBook(@PathVariable Long id, @RequestBody KnigaDTO knigaDTO){
        return bookService.edit(id, knigaDTO).map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/lendBook/{id}")
    public ResponseEntity<Kniga> lendBook(@PathVariable Long id){
        return bookService.lend(id).map(kniga -> ResponseEntity.ok().body(kniga))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Kniga> deleteBook(@PathVariable Long id){
        bookService.deleteById(id);
        if(bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
