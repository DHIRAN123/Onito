package com.example.Onito.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class TambolaTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "ticket_numbers")
    private List<List<Integer>> numbers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<List<Integer>> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<List<Integer>> numbers) {
		this.numbers = numbers;
	}

    
}
