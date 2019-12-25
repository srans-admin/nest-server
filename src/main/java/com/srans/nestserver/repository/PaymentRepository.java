package com.srans.nestserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	
	@Autowired 
	public FloorRepository floorRepository = null ;
	
	@Autowired 
	public HostelRepository hostelRepository = null ;
	
	@Autowired 
	public RoomRepository roomRepository = null ; 
	
	@Query(value="SELECT room_type FROM ROOM WHERE id=?1", nativeQuery=true)
	public String roomtype(Long floor_id);
	
	
	@Query(value="SELECT room_name FROM ROOM WHERE id=?1", nativeQuery=true)
	public Long roomName(Long floor_id);
	
	
	@Query(value="SELECT room_rent FROM ROOM WHERE id=?1", nativeQuery=true)
	public Long roomRent(Long floor_id);
	
	
	
}