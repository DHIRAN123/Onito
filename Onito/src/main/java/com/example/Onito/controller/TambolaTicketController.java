package com.example.Onito.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Onito.model.TambolaTicket;
import com.example.Onito.services.TambolaTicketService;

@RestController
@RequestMapping("/api/tickets")
public class TambolaTicketController {
    private final TambolaTicketService ticketService;

    @Autowired
    public TambolaTicketController(TambolaTicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/generate/{numberOfSets}")
    public ResponseEntity<Map<String, List<List<Integer>>>> generateAndSaveTickets(@PathVariable int numberOfSets) {
        Map<String, List<List<Integer>>> response = ticketService.generateAndSaveTickets(numberOfSets);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TambolaTicket>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<TambolaTicket> tickets = ticketService.getAllTickets(page, size);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
}
