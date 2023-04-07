package practice.webshop

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.ArrayNode
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private val objectMapper = ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)

enum class Page {
    Categories,
    ProductsInCategory,
    ProductDetails,
    Exit,
}

fun main() {
    var openPage = Page.Categories
    var chosenCategory: String? = null
    mainLoop@ while (true) {
        when (openPage) {
            Page.Categories -> {
                println("PAGE: Categories")

                println("Welcome to my very professional webshop. Please choose a category by index: ")
                val responseBody = sendGetRequest("https://dummyjson.com/products/categories")
                val responseJson = objectMapper.readTree(responseBody) as ArrayNode
                for ((index, category) in responseJson.withIndex()) {
                    print(index)
                    println(category)
                }

                val input = readLine()!!
                if (input == "exit") {
                    openPage = Page.Exit
                } else {
                    chosenCategory = responseJson.get(input.toInt()).asText()
                    openPage = Page.ProductsInCategory
                }
            }
            Page.ProductsInCategory -> {
                println("PAGE: ProductsInCategory ($chosenCategory)")
            }
            Page.ProductDetails -> {
                println("PAGE: ProductDetails")
            }
            Page.Exit -> {
                println("PAGE: Exit")
                break@mainLoop
            }
        }
    }


}

fun sendGetRequest(uri: String): String {
    val request = HttpRequest.newBuilder()
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .uri(URI.create(uri))
        .build()
    val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())

    if (response.statusCode() != 200) {
        throw Exception("response code is ${response.statusCode()}")
    }

    return response.body()
}