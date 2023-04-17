package practice.simplehttpserver

import org.http4k.core.*
import org.http4k.routing.PathMethod
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import java.time.Instant


fun main() {
    val app = getRoutes()
    startServer(app)
}

fun getRoutes(): HttpHandler {
    var numberOfVisits = 0
    val stringList = ArrayList<String>()
    val listOfTreeNames = ArrayList<String>()
    val taskIdAutoIncrementer = AutoIncrementer()
    val tasks = ArrayList<Task>()

    listOfTreeNames.add("fenyo")
    listOfTreeNames.add("fuzfa")
    listOfTreeNames.add("platan")

    val task0 = Task(id = taskIdAutoIncrementer.getNextValue(), name = "cook", isDone = false)
    val task1 = Task(id = taskIdAutoIncrementer.getNextValue(), name = "read", isDone = true)
    val task2 = Task(id = taskIdAutoIncrementer.getNextValue(), name = "world domination", isDone = true)

    tasks.add(task0)
    tasks.add(task1)
    tasks.add(task2)

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
            return@to Response(Status.OK).body(listOfTreeNames.toString())
        },

        PathMethod("tree/add", Method.PUT) to {
            val requestBody: String = String(it.body.stream.readAllBytes())
            listOfTreeNames.add(requestBody)
            return@to Response(Status.OK).body("successfully added tree name")
        },
        PathMethod("tree/search", Method.GET) to {
            val params = explodeParams(it.uri.query)
            val filteredTreeNames = listOfTreeNames.filter { return@filter it.contains(params["textToSearch"]!!) }
            return@to Response(Status.OK).body(filteredTreeNames.toString())
        },
        PathMethod("tree/remove", Method.DELETE) to {
            val requestBody: String = String(it.body.stream.readAllBytes())
            listOfTreeNames.remove(requestBody)
            println(listOfTreeNames)
            return@to Response(Status.OK).body("Tree $requestBody has been removed")
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
            return@to Response(Status.OK).body(tasks.toString())
        },
        PathMethod("tasks/setState", Method.PATCH) to {
            return@to setState(it, tasks)
        },
        PathMethod("tasks/add", Method.PUT) to {
            val requestBody = String(it.body.stream.readAllBytes())
            val taskToAdd =
                Task(id = taskIdAutoIncrementer.getNextValue(), name = requestBody.toString(), isDone = false)
            tasks.add(taskToAdd)
            return@to Response(Status.OK).body("successfully added new task with id ${taskToAdd.id}")
        },
        PathMethod("tasks/remove", Method.DELETE) to {
            val taskIdToRemove = String(it.body.stream.readAllBytes()).toInt()

            tasks.removeIf { it.id == taskIdToRemove }

            return@to Response(Status.OK).body("task has been removed")
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

fun setState(request: Request, tasks: ArrayList<Task>): Response {
    val params = explodeParams(request.uri.query)
    val taskId = params["taskId"]!!.toInt()
    val newState = params["newState"]!!
    for (task in tasks) {
        if (taskId == task.id) {
            if (newState == "true") {
                task.isDone = true
            } else if (newState == "false") {
                task.isDone = false
            }
        }

    }

    return Response(Status.OK).body("state has been changed to $newState")
}