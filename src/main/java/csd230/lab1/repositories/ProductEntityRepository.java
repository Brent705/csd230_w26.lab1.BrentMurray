package csd230.lab1.repositories;

import csd230.lab1.entities.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEntityRepository {
    ProductEntity findProductEntityById(int id);
    List<ProductEntity> findAll();
}
