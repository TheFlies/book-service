package org.theflies.tkg.book

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.count
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class BookRepository(val template: ReactiveMongoTemplate,
                     val objectMapper: ObjectMapper) {

  private val log = LoggerFactory.getLogger(this.javaClass)

  fun initData() {
    if (count().block() == 0L) {
      val blogResource = ClassPathResource("data/books.json")
      val books: List<Book> = objectMapper.readValue(blogResource.inputStream)
      books.forEach { save(it).block() }
      log.info("Book data initialization complete")
    }
  }

  fun findAll(): Flux<Book> {
    val query  = Query()
    query.with(Sort.by(Sort.Order(DESC, "title")))
    return template.find(query)
  }

  fun findOne(id: String) = template.findById<Book>(id)

  fun save(book: Book) = template.save(book)!!

  fun count() = template.count<Book>()
}