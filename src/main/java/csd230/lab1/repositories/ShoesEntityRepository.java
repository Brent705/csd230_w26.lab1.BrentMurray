package csd230.lab1.repositories;

import csd230.lab1.entities.ShoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoesEntityRepository extends JpaRepository<ShoesEntity, Long> {
    List<ShoesEntity> findByBrand(String brand);
    List<ShoesEntity> findBySize(String size);
    List<ShoesEntity> findByHighTop(boolean highTop);
    List<ShoesEntity> findByBrandAndHighTop(String brand, boolean highTop);
}