package com.sts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sts.binding.Passenger;
import com.sts.binding.Ticket;
import com.sts.service.TicketService;

import reactor.core.publisher.Mono;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	private TicketService tservice;
	
	@PostMapping(
			value="/mt",
			consumes = "application/json",
			produces = "application/json"
			)
	public ResponseEntity<Mono<Ticket>>bookTicket(@RequestBody Passenger p ){
		
		Mono<Ticket>b=tservice.bootkTicket(p);
		return new ResponseEntity<>(b,HttpStatus.CREATED);
	}
	
	
	@GetMapping(value="/mkt",
			produces = "application/json"
			)
	public ResponseEntity<Ticket[]> getAllTickets(Ticket t ){
		Mono<Ticket[]> Tickets = tservice.getAllTickets();
		return new ResponseEntity(Tickets,HttpStatus.OK);
		}
		
	@GetMapping(value="mktt/{tid}",
			consumes = "application/json",
			produces = "application/json"
			)
	public ResponseEntity<Ticket>geTicket(@PathVariable Integer ticketId){
		Mono<Ticket> t = tservice.getTicket(ticketId);
		return new ResponseEntity(t, HttpStatus.OK);
	}
}

	
