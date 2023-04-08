package practice.webshop

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
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
    Cart
}

fun main() {
    val cart = Cart()
    var openPage = Page.Categories
    var chosenCategory: String? = null
    var chosenProductId: Int? = null
    mainLoop@ while (true) {
        when (openPage) {
            Page.Categories -> {
                println("PAGE: Categories")

                println("Welcome to my very professional webshop. Please choose a category by index or type 'exit': ")
                val responseBody = sendGetRequest("https://dummyjson.com/products/categories")
                val responseJson = objectMapper.readTree(responseBody) as ArrayNode
                for ((index, category) in responseJson.withIndex()) {
                    println("$index: ${category.asText()}")
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
                val responseBody = sendGetRequest("https://dummyjson.com/products/category/$chosenCategory")
                val responseJson = objectMapper.readTree(responseBody) as ObjectNode
                val productsJson = responseJson.get("products") as ArrayNode

                for (product in productsJson) {
                    val productId = product.get("id").asInt()
                    val productTitle = product.get("title").asText()
                    println("$productId: $productTitle")
                }
                println("Choose a product by id, press 'c' to go to categories or type 'exit' to quit: ")
                val input = readLine()!!

                when (input) {
                    "c" -> openPage = Page.Categories
                    "exit" -> Page.Exit
                    else -> {
                        chosenProductId = input.toInt()
                        openPage = Page.ProductDetails
                    }

                }

            }
            Page.ProductDetails -> {
                println("PAGE: ProductDetails ($chosenProductId)")
                val responseBody = sendGetRequest("https://dummyjson.com/products/$chosenProductId")
                val responseJson = objectMapper.readTree(responseBody) as ObjectNode
                println("Here is your chosen product:")
                println("Name: ${responseJson.get("title").asText()}")
                println("ID: ${responseJson.get("id").asInt()}")
                println("Price: ${responseJson.get("price").asInt()}")
                println("Rating: ${responseJson.get("rating").asInt()}")
                println("Brand: ${responseJson.get("brand").asText()}")
                println("Category: ${responseJson.get("category").asText()}")
                println("Thumbnail: ${responseJson.get("thumbnail").asText()}")

                println("Press 'p' to put this item in your cart,\npress 'l' to go the list of products in the chosen category\npress 'c' to view your cart\nor type 'exit' to quit: ")
                val input = readLine()!!

                when (input) {
                    "l" -> openPage = Page.ProductsInCategory
                    "exit" -> openPage = Page.Exit
                    "c" -> openPage = Page.Cart
                    "p" -> {
                        cart.addProduct(chosenProductId!!)
                        println("Your item has been added to your cart, press 'c' to go to cart or press anything else to continue shopping")
                        val answer = readLine()!!
                        if (answer == "c") {
                            openPage = Page.Cart
                        }else{
                            openPage = Page.ProductDetails
                        }
                    }

                }


            }
            Page.Exit -> {
                println("PAGE: Exit")
                println("See you later alligator")
                break@mainLoop
            }
            Page.Cart -> {
                println("---------------------------------------------")
                println("Your cart contains the following items: ")
                for (item in cart.productIds) {
                    val responseBody = sendGetRequest("https://dummyjson.com/products/$item")
                    val responseJson = objectMapper.readTree(responseBody) as ObjectNode
                    println("$item: ${responseJson.get("title").asText()}")

                }

                println("---------------------------------------------")
                println("Press 'b' to go back to the product details or type 'exit' to quit: ")

                val input = readLine()!!
                when (input) {
                    "b" -> openPage = Page.ProductDetails
                    "exit" -> openPage = Page.Exit
                }
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