package com.example.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.models.Lek;

@Repository
public interface LekRepository extends JpaRepository<Lek, Integer> {
	List<Lek> findByNazivContainingIgnoreCase(String naziv);

	boolean existsByNaziv(String naziv);

	void deleteById(Long id);

	Optional<Lek> findById(Long id);

}

