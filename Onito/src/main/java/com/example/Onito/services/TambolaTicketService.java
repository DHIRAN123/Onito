package com.example.Onito.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.Onito.model.TambolaTicket;
import com.example.Onito.repository.TambolaTicketRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TambolaTicketService {
    private final TambolaTicketRepository ticketRepository;

    @Autowired
    public TambolaTicketService(TambolaTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Map<String, List<List<Integer>>> generateAndSaveTickets(int numberOfSets) {
        Map<String, List<List<Integer>>> response = new HashMap<>();

        try {
            for (int i = 0; i < numberOfSets; i++) {
                TambolaTicket ticket = generateUniqueTicket();
                ticketRepository.save(ticket);

                // Convert the ticket id to a string for the response
                String ticketId = String.valueOf(ticket.getId());

                // Add the ticket numbers to the response map
                response.put(ticketId, ticket.getNumbers());
            }
        } catch (DataIntegrityViolationException e) {
            // Handle database constraint violations
            // You can log the error or throw a custom exception if needed
            throw new RuntimeException("Error saving Tambola tickets to the database.", e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new RuntimeException("An unexpected error occurred while generating Tambola tickets.", e);
        }

        return response;
    }
        private TambolaTicket generateUniqueTicket() {
            TambolaTicket ticket = new TambolaTicket();
            List<List<Integer>> numbers = new ArrayList<>();

            
            for (int i = 0; i < 3; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < 9; j++) {
                    row.add(0);
                }
                numbers.add(row);
            }

            List<Integer> allNumbers = new ArrayList<>();
            for (int num = 1; num <= 90; num++) {
                allNumbers.add(num);
            }

                        for (int col = 0; col < 9; col++) {
                Collections.shuffle(allNumbers);
                for (int row = 0; row < 3; row++) {
                    
                    if (Math.random() <= 0.3) {
                        numbers.get(row).set(col, 0);
                    } else {
                        numbers.get(row).set(col, allNumbers.remove(0));
                    }
                }
            }

            ticket.setNumbers(numbers);
            return ticket;
        }

    public List<TambolaTicket> getAllTickets(int page, int size) {

        return ticketRepository.findAll();
    }
}
