package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Floor;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
	/*
	 * List<Floor> findByHostelId(Long hostel_id); Optional<Floor>
	 * findByIdAndHostelId(Long floor_id, Long hostel_id);
	 */
}