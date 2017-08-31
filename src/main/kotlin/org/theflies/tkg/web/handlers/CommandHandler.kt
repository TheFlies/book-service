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
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Component
@EnableBinding(Source::class)
class CommandHandler(val channels: Source) {

  fun changeBook(serverRequest: ServerRequest): Mono<ServerResponse> {
    // TODO: create event with changes from server request
    // TODO: publish event into a specific topic
//    channels.output().send(MessageBuilder.createMessage(ChangeBookEvent(serverRequest.bodyToMono(Book::class.java)
//        .block())))

    return accepted().build()
  }
}
