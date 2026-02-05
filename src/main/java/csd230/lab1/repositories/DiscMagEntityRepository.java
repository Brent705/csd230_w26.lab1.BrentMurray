package csd230.lab1.repositories;

import csd230.lab1.entities.DiscMagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscMagEntityRepository extends JpaRepository<DiscMagEntity, Long> {
    DiscMagEntity findByName(String name);
    DiscMagEntity findByDescription(String description);
}