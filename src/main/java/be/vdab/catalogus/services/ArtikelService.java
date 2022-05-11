package be.vdab.catalogus.services;

import be.vdab.catalogus.domain.Artikel;
import be.vdab.catalogus.events.ArtikelGemaakt;
import be.vdab.catalogus.repositories.ArtikelRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArtikelService {
    private final ArtikelRepository repository;
    private final AmqpTemplate template;

    public ArtikelService(ArtikelRepository repository, AmqpTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    public void create(Artikel artikel) {
        repository.save(artikel);
        template.convertAndSend("catalogus", null, new ArtikelGemaakt(artikel));
    }
}
