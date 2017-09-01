package org.theflies.tkg.web.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.router
import org.theflies.tkg.web.handlers.BookHandler
import org.theflies.tkg.web.handlers.CommandHandler

@Configuration
class ApiRoutes(val bookHandler: BookHandler, var commandHandler: CommandHandler) {

  @Bean
  fun apiRouter() = router {
    (accept(APPLICATION_JSON) and "/api").nest {
      "/books".nest {
        GET("/", bookHandler::findAll)
        GET("/{id}", bookHandler::findOne)
        PATCH("/", commandHandler::changeBook)
        POST("/", commandHandler::createBook)
      }
    }
  }
}

