package com.srans.nestserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Floors;

@Repository
public interface FloorRepository extends JpaRepository<Floors, Long> {
    List<Floors> findByHostelId(Long hostel_id);
	 Optional<Floors> findByIdAndHostelId(Long floor_id, Long hostel_id); 
}