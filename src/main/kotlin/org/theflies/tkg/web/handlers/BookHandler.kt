package org.theflies.tkg.web.handlers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.theflies.tkg.book.Book
import org.theflies.tkg.book.BookRepository
import org.theflies.tkg.util.json


@Component
class BookHandler(val bookRepository: BookRepository) {
  fun findAll(req: ServerRequest) = ok().json().body(bookRepository.findAll(), Book::class.java)
  fun findOne(req: ServerRequest) = ok().json().body(bookRepository.findOne(req.pathVariable("id")))
}

