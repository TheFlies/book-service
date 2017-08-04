package org.theflies.tkg.book

import java.time.ZonedDateTime

data class Book(
    var id: Long,
    var title: String,
    var description: String,
    var author: String,
    var volume: Int?,
    var released: ZonedDateTime?,
    var chapter: Int?,
    var status: BookStatus
)

enum class BookStatus {
  UNKNOWN,
  ONGOING,
  COMPLETED,
  DROPPED
}
