package be.vdab.catalogus.controllers;

import be.vdab.catalogus.domain.Artikel;
import be.vdab.catalogus.services.ArtikelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artikels")
class ArtikelController {
    private final ArtikelService artikelService;

    ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void post(@RequestBody String naam) {
        artikelService.create(new Artikel(naam));
    }
}
