package csd230.lab1;

// Test won't run properly 🤷‍♂️

import csd230.lab1.entities.*;
import csd230.lab1.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EntityScan(basePackages = "csd230.lab1.entities")
@EnableJpaRepositories(basePackages = "csd230.lab1.repositories")
@ActiveProfiles("test")
class BoxingGearEntityTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GlovesEntityRepository glovesRepository;

    @Autowired
    private ShoesEntityRepository shoesRepository;

    @Autowired
    private CartEntityRepository cartRepository;

    @Test
    void testCreateGlovesEntity() {
        GlovesEntity gloves = new GlovesEntity("L", "Cleto Reyes", 299.99, 10);
        GlovesEntity saved = glovesRepository.save(gloves);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getSize()).isEqualTo("L");
        assertThat(saved.getBrand()).isEqualTo("Cleto Reyes");
        assertThat(saved.getPrice()).isEqualTo(299.99);
        assertThat(saved.getWeightOz()).isEqualTo(10);
    }

    @Test
    void testReadGlovesEntity() {
        GlovesEntity gloves = new GlovesEntity("M", "Winning", 529.99, 16);
        entityManager.persist(gloves);
        entityManager.flush();
        Optional<GlovesEntity> found = glovesRepository.findById(gloves.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getBrand()).isEqualTo("Winning");
        assertThat(found.get().getWeightOz()).isEqualTo(16);
    }

    @Test
    void testUpdateGlovesEntity() {
        GlovesEntity gloves = new GlovesEntity("S", "Stin", 299.99, 12);
        entityManager.persist(gloves);
        entityManager.flush();
        gloves.setPrice(279.99);
        gloves.setBrand("Sting");
        GlovesEntity updated = glovesRepository.save(gloves);
        assertThat(updated.getPrice()).isEqualTo(279.99);
        assertThat(updated.getBrand()).isEqualTo("Sting");
    }

    @Test
    void testDeleteGlovesEntity() {
        GlovesEntity gloves = new GlovesEntity("S", "Grant", 249.99, 10);
        entityManager.persist(gloves);
        entityManager.flush();
        Long id = gloves.getId();
        glovesRepository.deleteById(id);
        Optional<GlovesEntity> found = glovesRepository.findById(id);
        assertThat(found).isEmpty();
    }

    @Test
    void testFindGlovesByBrand() {
        GlovesEntity gloves1 = new GlovesEntity("L", "Everlast", 89.99, 12);
        GlovesEntity gloves2 = new GlovesEntity("M", "Everlast", 79.99, 10);
        GlovesEntity gloves3 = new GlovesEntity("L", "Title", 119.99, 12);
        entityManager.persist(gloves1);
        entityManager.persist(gloves2);
        entityManager.persist(gloves3);
        entityManager.flush();
        List<GlovesEntity> everlastGloves = glovesRepository.findByBrand("Everlast");
        assertThat(everlastGloves).hasSize(2);
        assertThat(everlastGloves).extracting(GlovesEntity::getBrand)
                .containsOnly("Everlast");
    }

    @Test
    void testFindGlovesByWeightOz() {
        GlovesEntity gloves1 = new GlovesEntity("L", "Venum", 89.99, 12);
        GlovesEntity gloves2 = new GlovesEntity("M", "RDX", 99.99, 16);
        GlovesEntity gloves3 = new GlovesEntity("L", "Everlast", 109.99, 12);
        entityManager.persist(gloves1);
        entityManager.persist(gloves2);
        entityManager.persist(gloves3);
        entityManager.flush();
        List<GlovesEntity> twelveOzGloves = glovesRepository.findByWeightOz(12);
        assertThat(twelveOzGloves).hasSize(2);
        assertThat(twelveOzGloves).extracting(GlovesEntity::getWeightOz)
                .containsOnly(12);
    }

    @Test
    void testFindGlovesBySize() {
        GlovesEntity gloves1 = new GlovesEntity("L", "Venum", 89.99, 12);
        GlovesEntity gloves2 = new GlovesEntity("M", "Everlast", 89.99, 12);
        GlovesEntity gloves3 = new GlovesEntity("L", "Cleto Reyes", 309.99, 8);
        entityManager.persist(gloves1);
        entityManager.persist(gloves2);
        entityManager.persist(gloves3);
        entityManager.flush();
        List<GlovesEntity> largeGloves = glovesRepository.findBySize("L");
        assertThat(largeGloves).hasSize(2);
        assertThat(largeGloves).extracting(GlovesEntity::getSize)
                .containsOnly("L");
    }

    @Test
    void testFindGlovesByBrandAndWeightOz() {
        GlovesEntity gloves1 = new GlovesEntity("L", "Everlast", 89.99, 12);
        GlovesEntity gloves2 = new GlovesEntity("M", "Everlast", 99.99, 16);
        GlovesEntity gloves3 = new GlovesEntity("L", "Title", 109.99, 12);
        entityManager.persist(gloves1);
        entityManager.persist(gloves2);
        entityManager.persist(gloves3);
        entityManager.flush();
        List<GlovesEntity> result = glovesRepository.findByBrandAndWeightOz("Everlast", 12);
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBrand()).isEqualTo("Everlast");
        assertThat(result.get(0).getWeightOz()).isEqualTo(12);
    }

    @Test
    void testCreateShoesEntity() {
        ShoesEntity shoes = new ShoesEntity("10", "Nike HyperKO", 249.99, true);
        ShoesEntity saved = shoesRepository.save(shoes);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getSize()).isEqualTo("10");
        assertThat(saved.getBrand()).isEqualTo("Nike HyperKO");
        assertThat(saved.getPrice()).isEqualTo(249.99);
        assertThat(saved.isHighTop()).isTrue();
    }

    @Test
    void testReadShoesEntity() {
        ShoesEntity shoes = new ShoesEntity("9.5", "Adidas Boxhog", 279.99, true);
        entityManager.persist(shoes);
        entityManager.flush();
        Optional<ShoesEntity> found = shoesRepository.findById(shoes.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getBrand()).isEqualTo("Adidas Boxhog");
        assertThat(found.get().isHighTop()).isTrue();
    }

    @Test
    void testUpdateShoesEntity() {
        ShoesEntity shoes = new ShoesEntity("11", "Venum Contender", 99.99, false);
        entityManager.persist(shoes);
        entityManager.flush();
        shoes.setPrice(89.99);
        shoes.setHighTop(true);
        ShoesEntity updated = shoesRepository.save(shoes);
        assertThat(updated.getPrice()).isEqualTo(89.99);
        assertThat(updated.isHighTop()).isTrue();
    }

    @Test
    void testDeleteShoesEntity() {
        ShoesEntity shoes = new ShoesEntity("10.5", "Everlast Elite 2", 129.99, true);
        entityManager.persist(shoes);
        entityManager.flush();
        Long id = shoes.getId();
        shoesRepository.deleteById(id);
        Optional<ShoesEntity> found = shoesRepository.findById(id);
        assertThat(found).isEmpty();
    }

    @Test
    void testFindShoesByBrand() {
        ShoesEntity shoes1 = new ShoesEntity("10", "Nike Machomai", 249.99, true);
        ShoesEntity shoes2 = new ShoesEntity("9.5", "Hayabusa", 139.99, false);
        ShoesEntity shoes3 = new ShoesEntity("11", "RDX", 79.99, true);
        entityManager.persist(shoes1);
        entityManager.persist(shoes2);
        entityManager.persist(shoes3);
        entityManager.flush();
        List<ShoesEntity> adidasShoes = shoesRepository.findByBrand("Nike Machomai");
        assertThat(adidasShoes).hasSize(1);
        assertThat(adidasShoes).extracting(ShoesEntity::getBrand)
                .containsOnly("Nike Machomai");
    }

    @Test
    void testFindShoesBySize() {
        ShoesEntity shoes1 = new ShoesEntity("10", "Brand1", 149.99, true);
        ShoesEntity shoes2 = new ShoesEntity("9.5", "Brand2", 139.99, false);
        ShoesEntity shoes3 = new ShoesEntity("10", "Brand3", 159.99, true);
        entityManager.persist(shoes1);
        entityManager.persist(shoes2);
        entityManager.persist(shoes3);
        entityManager.flush();
        List<ShoesEntity> sizeTenShoes = shoesRepository.findBySize("10");
        assertThat(sizeTenShoes).hasSize(2);
        assertThat(sizeTenShoes).extracting(ShoesEntity::getSize)
                .containsOnly("10");
    }

    @Test
    void testFindShoesByHighTop() {
        ShoesEntity shoes1 = new ShoesEntity("10", "Brand1", 149.99, true);
        ShoesEntity shoes2 = new ShoesEntity("9.5", "Brand2", 139.99, false);
        ShoesEntity shoes3 = new ShoesEntity("11", "Brand3", 159.99, true);
        entityManager.persist(shoes1);
        entityManager.persist(shoes2);
        entityManager.persist(shoes3);
        entityManager.flush();
        List<ShoesEntity> highTopShoes = shoesRepository.findByHighTop(true);
        List<ShoesEntity> lowTopShoes = shoesRepository.findByHighTop(false);
        assertThat(highTopShoes).hasSize(2);
        assertThat(lowTopShoes).hasSize(1);
        assertThat(highTopShoes).extracting(ShoesEntity::isHighTop)
                .containsOnly(true);
    }

    @Test
    void testFindShoesByBrandAndHighTop() {
        ShoesEntity shoes1 = new ShoesEntity("10", "Nike Hyperkos", 149.99, true);
        ShoesEntity shoes2 = new ShoesEntity("9.5", "Nike Machomai", 139.99, false);
        ShoesEntity shoes3 = new ShoesEntity("11", "Adidas Boxhog", 159.99, false);
        entityManager.persist(shoes1);
        entityManager.persist(shoes2);
        entityManager.persist(shoes3);
        entityManager.flush();
        List<ShoesEntity> nikeHighTops = shoesRepository.findByBrandAndHighTop("Nike Hyperkos", true);
        assertThat(nikeHighTops).hasSize(1);
        assertThat(nikeHighTops.get(0).getBrand()).isEqualTo("Nike Hyperkos");
        assertThat(nikeHighTops.get(0).isHighTop()).isTrue();
    }

    @Test
    void testAddGlovesToCart() {
        GlovesEntity gloves = new GlovesEntity("L", "Everlast", 89.99, 12);
        CartEntity cart = new CartEntity();
        cart.addProduct(gloves);
        CartEntity savedCart = cartRepository.save(cart);
        assertThat(savedCart.getProducts()).hasSize(1);
        assertThat(savedCart.getProducts()).contains(gloves);
    }

    @Test
    void testAddShoesToCart() {
        ShoesEntity shoes = new ShoesEntity("10", "Adidas Boxhog", 149.99, true);
        CartEntity cart = new CartEntity();
        cart.addProduct(shoes);
        CartEntity savedCart = cartRepository.save(cart);
        assertThat(savedCart.getProducts()).hasSize(1);
        assertThat(savedCart.getProducts()).contains(shoes);
    }

    @Test
    void testAddMultipleBoxingGearToCart() {
        GlovesEntity gloves = new GlovesEntity("L", "Everlast", 89.99, 12);
        ShoesEntity shoes = new ShoesEntity("10", "Adidas Boxhog", 149.99, true);
        CartEntity cart = new CartEntity();
        cart.addProduct(gloves);
        cart.addProduct(shoes);
        CartEntity savedCart = cartRepository.save(cart);
        assertThat(savedCart.getProducts()).hasSize(2);
        assertThat(savedCart.getProducts()).containsExactlyInAnyOrder(gloves, shoes);
    }

    @Test
    void testCartPersistsBoxingGearRelationship() {
        GlovesEntity gloves = new GlovesEntity("M", "Title", 129.99, 16);
        ShoesEntity shoes = new ShoesEntity("9.5", "Nike Hyperkos", 179.99, true);
        CartEntity cart = new CartEntity();
        cart.addProduct(gloves);
        cart.addProduct(shoes);
        entityManager.persist(cart);
        entityManager.flush();
        entityManager.clear();
        CartEntity foundCart = cartRepository.findById(cart.getId()).orElse(null);
        assertThat(foundCart).isNotNull();
        assertThat(foundCart.getProducts()).hasSize(2);
        assertThat(foundCart.getProducts())
                .extracting(p -> p.getClass().getSimpleName())
                .containsExactlyInAnyOrder("GlovesEntity", "ShoesEntity");
    }

    @Test
    void testGlovesSellItem() {
        GlovesEntity gloves = new GlovesEntity("L", "Everlast", 89.99, 12);
        gloves.sellItem();
    }

    @Test
    void testShoesSellItem() {
        ShoesEntity shoes = new ShoesEntity("10", "Adidas Boxhog", 149.99, true);
        shoes.sellItem();
    }

    @Test
    void testGlovesGetPrice() {
        GlovesEntity gloves = new GlovesEntity("L", "Everlast", 89.99, 12);
        double price = gloves.getPrice();
        assertThat(price).isEqualTo(89.99);
    }

    @Test
    void testShoesGetPrice() {
        ShoesEntity shoes = new ShoesEntity("10", "Adidas Boxhog", 149.99, true);
        double price = shoes.getPrice();
        assertThat(price).isEqualTo(149.99);
    }

    @SpringBootConfiguration
    @EnableAutoConfiguration
    static class TestConfig {
    }
}