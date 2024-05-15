package com.example.bookservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.command.DeleteBookCommand;
import com.example.bookservice.command.command.UpdateBookCommand;
import com.example.bookservice.command.event.BookCreatedEvent;
import com.example.bookservice.command.event.BookDeletedEvent;
import com.example.bookservice.command.event.BookUpdatedEvent;
import com.example.commonservice.command.UpdateStatusBookCommand;
import com.example.commonservice.event.BookUpdateStatusEvent;

@Aggregate
public class BookAggreagte {
	@AggregateIdentifier
	private String bookId;
	private String name;
	private String author;
	private Boolean isReady;

	public BookAggreagte() {

	}

	@CommandHandler
	public BookAggreagte(CreateBookCommand createBookCommand) {
		BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
		BeanUtils.copyProperties(createBookCommand, bookCreatedEvent);
		AggregateLifecycle.apply(bookCreatedEvent);
	}

	@CommandHandler
	public void handle(UpdateBookCommand updateBookCommand) {
		BookUpdatedEvent event = new BookUpdatedEvent();
		BeanUtils.copyProperties(updateBookCommand, event);

		AggregateLifecycle.apply(event);
	}

	@CommandHandler
	public void handle(DeleteBookCommand deleteBookCommand) {
		BookDeletedEvent event = new BookDeletedEvent();
		BeanUtils.copyProperties(deleteBookCommand, event);

		AggregateLifecycle.apply(event);
	}

	@CommandHandler
	public void handle(UpdateStatusBookCommand command) {
		BookUpdateStatusEvent event = new BookUpdateStatusEvent();
		BeanUtils.copyProperties(command, event);
		AggregateLifecycle.apply(event);
	}

	@EventSourcingHandler
	public void on(BookUpdateStatusEvent event) {
		this.bookId = event.getBookId();
		this.isReady = event.getIsReady();
	}

	@EventSourcingHandler
	public void on(BookCreatedEvent event) {
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.isReady = event.getIsReady();
		this.name = event.getName();
	}

	@EventSourcingHandler
	public void on(BookUpdatedEvent event) {
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.name = event.getName();
		this.isReady = event.getIsReady();
	}

	@EventSourcingHandler
	public void on(BookDeletedEvent event) {
		this.bookId = event.getBookId();
	}
}
