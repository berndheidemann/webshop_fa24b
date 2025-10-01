package de.szut.webshop.article;


import de.szut.webshop.contact.ContactEntity;
import de.szut.webshop.exceptions.ResourceNotFoundException;
import de.szut.webshop.supplier.SupplierEntity;
import de.szut.webshop.supplier.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;


@Service
public class ArticleService implements ApplicationRunner {

    private ArticleRepository articleRepository;
    private SupplierRepository supplierRepository;
    private CurrencyAPIConversionService currencyService;


    public ArticleService (ArticleRepository repository, SupplierRepository supplierRepository, CurrencyAPIConversionService currencyService) {
        this.supplierRepository = supplierRepository;
        this.articleRepository = repository;
        this.currencyService= currencyService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        generateTestData();
    }

    @Transactional
    public void generateTestData() {
        var contact=new ContactEntity();
        contact.setCity("Bremen");
        contact.setPostcode("28359");
        contact.setStreet("Hollerallee 34");
        contact.setPhone("0421 123456");

        var supp=new SupplierEntity();
        supp.setName("Becks");
        supp.setContact(contact);

        var sampleSupplier = supplierRepository.save(supp);

        // generate five sample articles and persist them
        for (int i = 1; i <= 5; i++) {
            ArticleEntity article = new ArticleEntity();
            article.setDesignation("Sample Article " + i);
            article.setPrice(10.0 * i);
            article.setSupplier(sampleSupplier);  // nicht supp

            articleRepository.save(article);
        }
    }

    public ArticleEntity readById(Long id, String currency) {
        var entity = articleRepository.findById(id).orElse(null);
        entity.setPrice(currencyService.convert("EUR", currency, entity.getPrice()));
        return entity;
    }

    public ArticleEntity readById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public ArticleEntity save(ArticleEntity entity) {
        return articleRepository.save(entity);
    }

    public ArticleEntity save(Long supplierId, ArticleEntity entity) {
        var supplier = supplierRepository.findById(supplierId).orElseThrow(()-> new ResourceNotFoundException(supplierId));
        entity.setSupplier(supplier);
        return this.articleRepository.save(entity);
    }

    public Set<ArticleEntity> readAll() {
        return new HashSet<>(articleRepository.findAll());
    }

    public void delete(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        articleRepository.deleteById(id);
    }
}
