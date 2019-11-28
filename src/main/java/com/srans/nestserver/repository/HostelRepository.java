package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Hostels;

@Repository
public interface HostelRepository extends JpaRepository<Hostels, Long> {

}