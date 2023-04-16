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

fun startServer(app: HttpHandler) {
    val server: Http4kServer = app.asServer(SunHttp(port = 3000))
    server.start()
    println("Server started on port: ${server.port()}")
}