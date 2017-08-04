package org.theflies.tkg.util

import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.ServerResponse

fun ServerResponse.BodyBuilder.json() = contentType(APPLICATION_JSON_UTF8)