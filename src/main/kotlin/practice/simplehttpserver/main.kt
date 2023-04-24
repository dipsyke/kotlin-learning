package practice.simplehttpserver

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.http4k.core.*
import org.http4k.routing.PathMethod
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import practice.tubbydatasource.TubbyDataSource
import java.io.File
import java.time.Instant
import kotlin.random.Random

private val dataSource = TubbyDataSource(File("C:\\tmp\\simplehttpserver\\tubbydatasource.json"))
private val objectMapper = ObjectMapper()

fun main() {
    val app = getRoutes()
    startServer(app)
}

fun getRoutes(): HttpHandler {
    var numberOfVisits = 0
    val stringList = ArrayList<String>()


    if (dataSource.getAll(Tree::class.java).isEmpty()) {
        dataSource.save(Tree(name = "fenyo", age = 34))
        dataSource.save(Tree(name = "fuzfa", age = 78))
        dataSource.save(Tree(name = "platan", age = 120))
    }

    if (dataSource.getAll(Task::class.java).isEmpty()) {
        dataSource.save(Task(name = "cook", isDone = false))
        dataSource.save(Task(name = "read", isDone = true))
        dataSource.save(Task(name = "world domination", isDone = true))
    }

    if (dataSource.getAll(Product::class.java).isEmpty()) {
        dataSource.save(Product(name = "product1"))
        dataSource.save(Product(name = "product2"))
        dataSource.save(Product(name = "product3"))
        dataSource.save(Product(name = "product4"))
    }

    return routes(
        PathMethod("/asd", Method.GET) to {
            println("called /asd")
            return@to Response(Status.OK).body("Szeretem a dipszyt")
        },
        PathMethod("/tinky-winky", Method.GET) to {
            return@to Response(Status.OK).body("Szeretem csapkodni a Dipszy feneke't")
        },
        PathMethod("/time", Method.GET) to {
            return@to Response(Status.OK).body(Instant.now().toString())
        },
        PathMethod("/sum", Method.GET) to {
            return@to handleSumEndpoint(request = it)
        },
        PathMethod("blabla", Method.GET) to {
            return@to Response(Status.OK).body("babbbalalablabla")
        },
        PathMethod("count", Method.GET) to {
            numberOfVisits += 1
            println("count endpoint called, current number of visits: $numberOfVisits")
            return@to Response(Status.OK).body("number of visits = $numberOfVisits")
        },

        PathMethod("list", Method.GET) to {
            val params: HashMap<String, String> = explodeParams(it.uri.query)
            stringList.add(params["toAdd"]!!)
//            var body = ""
//
//            for (item in stringList) {
//                body += "$item,"
//            }
//            return@to Response(Status.OK).body(body)
            return@to Response(Status.OK).body(stringList.toString())

        },

        PathMethod("postTest", Method.POST) to {
            val requestBody: String = String(it.body.stream.readAllBytes())

            return@to Response(Status.OK).body("received this request body: $requestBody")

        },
        PathMethod("tree/list", Method.GET) to {
            return@to Response(Status.OK).body(dataSource.getAll(Tree::class.java).toString())
        },

        PathMethod("tree/add", Method.PUT) to {
            val requestBody: String = String(it.body.stream.readAllBytes())
            val treeToAdd = Tree(name = requestBody.toString(), age = Random.nextInt(1, 100))
            dataSource.save(treeToAdd)
            return@to Response(Status.OK).body("successfully added tree name")
        },
        PathMethod("tree/search", Method.GET) to {
            val params = explodeParams(it.uri.query)
            val filteredTreeNames =
                dataSource.getAll(Tree::class.java).filter { return@filter it.name.contains(params["textToSearch"]!!) }
            return@to Response(Status.OK).body(filteredTreeNames.toString())
        },
        PathMethod("tree/remove", Method.DELETE) to {
            val treeNameToRemove: String = String(it.body.stream.readAllBytes())
            val treeEntityToRemove = dataSource.getAll(Tree::class.java).firstOrNull { treeNameToRemove == it.name }
            if (treeEntityToRemove == null) {
                return@to Response(Status.NOT_FOUND).body("Tree with name $treeNameToRemove does not exist")
            }
            dataSource.deleteById(Tree::class.java, treeEntityToRemove.id!!)
            return@to Response(Status.OK).body("Tree ${treeEntityToRemove.id} with name $treeNameToRemove has been removed")
        },
        PathMethod("startYoutubeVideo", Method.GET) to {
            val params = explodeParams(it.uri.query)
            openYoutubeVideoInBrowser(params["videoId"]!!)
            return@to Response(Status.OK)
        },
        PathMethod("testHtml", Method.GET) to {
            val videoId = "6jpDPtoadzI"
            return@to Response(Status.OK).body(
                """
                <html>
                    <h1>Title h1</h1>
                    <p>This is a paragraph</p>
                    <p style="color:red">This is a red paragraph</p>
                    <a href="https://youtube.com/watch?v=$videoId">Click this link to go to YouTube</a>
                </html>
            """.trimIndent()
            )
        },

        PathMethod("tasks/list", Method.GET) to {
            return@to Response(Status.OK).body(dataSource.getAll(Task::class.java).toString())
        },
        PathMethod("tasks/setState", Method.PATCH) to {
            return@to setState(it)
        },
        PathMethod("tasks/add", Method.PUT) to {
            val requestBody = String(it.body.stream.readAllBytes())
            val taskToAdd = Task(name = requestBody.toString(), isDone = false)

            dataSource.save(taskToAdd)
            return@to Response(Status.OK).body("successfully added new task with id ${taskToAdd.id}")
        },
        PathMethod("tasks/remove", Method.DELETE) to {
            val taskIdToRemove = String(it.body.stream.readAllBytes()).toInt()

            dataSource.deleteById(Task::class.java, taskIdToRemove)

            return@to Response(Status.OK).body("task has been removed")
        },
        PathMethod("testJsonBody", Method.POST) to {
            val requestBody = String(it.body.stream.readAllBytes())

            val requestJson: ObjectNode = objectMapper.readTree(requestBody) as ObjectNode

            val name = requestJson.get("name")?.asText()
            val age = requestJson.get("age")?.asInt()


            return@to Response(Status.OK).body("name is $name, age is $age")
        },

        PathMethod("product/list", Method.GET) to {
            return@to Response(Status.OK).body(dataSource.getAll(Product::class.java).toString())
        },

        PathMethod("product/add", Method.GET) to {
            val requestBody = String(it.body.stream.readAllBytes())
            val productToAdd = Product(name = requestBody)
            dataSource.save(productToAdd)
            return@to Response(Status.OK).body("Product with the name $productToAdd has been added")
        },

        PathMethod("product/remove", Method.DELETE) to {
            val productIdToRemove = String(it.body.stream.readAllBytes()).toInt()
            val productName = dataSource.getById(Product::class.java, productIdToRemove)!!.name

            dataSource.deleteById(Product::class.java, productIdToRemove)

            return@to Response(Status.OK).body("Product ${productName} with ID $productIdToRemove has been removed")
        },

        PathMethod("product/search", Method.GET) to {
            val params = explodeParams(it.uri.query)
            val filteredProductNames = dataSource.getAll(Product::class.java)
                .filter { return@filter it.name.contains(params["productToSearch"]!!) }
            return@to Response(Status.OK).body(filteredProductNames.toString())
        }

    )

}

fun handleSumEndpoint(request: Request): Response {
    println("called ${request.uri}")
    println("query: ${request.uri.query}")

    val params = explodeParams(request.uri.query)
    println("params: $params")

    val a = params["a"]!!.toInt()
    val b = params["b"]!!.toInt()
    val sum = a + b

    return Response(Status.OK).body("summary: $sum")
}

fun explodeParams(queryString: String): HashMap<String, String> {
    val params = HashMap<String, String>()

    val paramPairStrings = queryString.split("&")

    for (paramPairString in paramPairStrings) {
        val splitParam = paramPairString.split("=")

        val paramName = splitParam[0]
        val paramValue = splitParam[1]

        params[paramName] = paramValue
    }

    return params
}

fun openYoutubeVideoInBrowser(videoId: String) {
    openUrlInBrowser("https://www.youtube.com/watch?v=$videoId")
}

fun openUrlInBrowser(url: String) {
    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler $url")
}

fun startServer(app: HttpHandler) {
    val server: Http4kServer = app.asServer(SunHttp(port = 3000))
    server.start()
    println("Server started on port: ${server.port()}")
}

fun setState(request: Request): Response {
    val params = explodeParams(request.uri.query)
    val taskId = params["taskId"]!!.toInt()
    val newState = params["newState"]!!

    val task = dataSource.getById(Task::class.java, taskId)!!

    if (newState == "true") {
        task.isDone = true
    } else if (newState == "false") {
        task.isDone = false
    }

    dataSource.save(task)

    return Response(Status.OK).body("state has been changed to $newState")
}