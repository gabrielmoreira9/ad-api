package com.ad.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncianteRepository
        extends JpaRepository<AnuncianteEntity, Long> {
}
