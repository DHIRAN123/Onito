package com.example.Onito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Onito.model.TambolaTicket;

public interface TambolaTicketRepository extends JpaRepository<TambolaTicket,Long> {

	List<TambolaTicket> findAll();
    
}
