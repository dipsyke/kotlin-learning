package practice.http1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

private val objectMapper = ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)

fun main() {
    print("Which product are you interested in? Type its number: ")
    val productId = readLine()!!.toInt()

    val request = HttpRequest.newBuilder()
        .GET()
        .uri(URI.create("https://dummyjson.com/products/${productId}"))
        .build()


    val response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString())
    val responseBody = response.body()

    println("received responseBody: $responseBody")

    val responseJson = objectMapper.readTree(responseBody)

    println("Here is your product:")
    println("   ID: ${responseJson.get("id").asInt()}")
    println("   Title: ${responseJson.get("title").asText()}")
    println("   Thumbnail: ${responseJson.get("thumbnail").asText()}")

}