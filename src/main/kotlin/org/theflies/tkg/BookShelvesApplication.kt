package org.theflies.tkg

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class BookShelvesApplication

fun main(args: Array<String>) {
  SpringApplicationBuilder(BookShelvesApplication::class.java)
      .web(WebApplicationType.REACTIVE)
      .build().run(*args)
}
