package csd230.lab1.repositories;

import csd230.lab1.entities.GlovesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlovesEntityRepository extends JpaRepository<GlovesEntity, Long> {
    List<GlovesEntity> findByBrand(String brand);
    List<GlovesEntity> findByWeightOz(int weightOz);
    List<GlovesEntity> findBySize(String size);
    List<GlovesEntity> findByBrandAndWeightOz(String brand, int weightOz);
}