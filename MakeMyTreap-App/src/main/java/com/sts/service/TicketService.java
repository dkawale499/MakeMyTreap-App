package com.sts.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.sts.binding.Passenger;
import com.sts.binding.Ticket;
import com.sts.repo.TicketRepository;

import reactor.core.publisher.Mono;

@Service
public class TicketService {

	private TicketRepository repo;
	
	String bookUrl="http://localhost:8080/ticket";
	String getUrl="http://localhost:8080/tickets";
	
public Mono<Ticket>bootkTicket(Passenger p) {
		
		WebClient webClient = WebClient.create();
		
		return webClient.post()
						.uri(bookUrl)
						.body(BodyInserters.fromValue(p))
						.retrieve()
						.bodyToMono(Ticket.class);	
		
	}
	public Mono<Ticket[]> getAllTickets(){
		WebClient webClient = WebClient.create();
		return  webClient.get()
						.uri(getUrl)
						.retrieve()
						.bodyToMono(Ticket[].class);
	}
	public Mono<Ticket>getTicket( Integer ticketId){
		WebClient webClient = WebClient.create();
		return  webClient.get()
						.uri(getUrl)
						.retrieve()
						.bodyToMono(Ticket.class);
	}
}