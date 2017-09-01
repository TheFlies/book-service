package org.theflies.tkg.web.handlers

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.accepted
import org.theflies.tkg.book.Book
import org.theflies.tkg.book.BookRepository
import reactor.core.publisher.Mono
import java.util.*

@Component
@EnableBinding(Source::class)
class CommandHandler(val channels: Source, val repo: BookRepository) {

  fun changeBook(serverRequest: ServerRequest): Mono<ServerResponse> {
    // TODO: create event with changes from server request
    // TODO: publish event into a specific topic
    val book = serverRequest.bodyToMono(Book::class.java)
        .block()
    book?.let {
      channels.output().send(MessageBuilder.createMessage(BookChangedEvent(book),
          MessageHeaders(mapOf(Pair("aggrId",book.id)))))
    }

    return accepted().build()
  }

  fun createBook(serverRequest: ServerRequest): Mono<ServerResponse> {
    val book = serverRequest.bodyToMono(Book::class.java)
    repo.save(book).subscribe { createdBook(it) }
    return accepted().build()
  }

  private fun createdBook(book: Book?) {
    book?.id?.let {
      channels.output().send(MessageBuilder.createMessage(BookChangedEvent(book),
          MessageHeaders(mapOf(Pair("aggrId",book.id)))))
    } ?: let {
      channels.output().send(MessageBuilder.createMessage(BookCreateFailedEvent(),
          MessageHeaders(mapOf(Pair("aggrId",UUID.randomUUID())))))
    }
  }
}

class BookCreateFailedEvent
class BookChangedEvent(book: Book) {

}
