package org.theflies.tkg

import org.springframework.stereotype.Component
import org.theflies.tkg.book.BookRepository
import javax.annotation.PostConstruct

@Component
class DBInitializer(val bookRepository: BookRepository) {
  @PostConstruct
  fun init() {
    bookRepository.initData()
  }
}