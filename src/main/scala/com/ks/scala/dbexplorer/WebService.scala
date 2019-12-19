package com.ks.scala.dbexplorer

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import akka.stream.ActorMaterializer
import com.ks.scala.dbexplorer.config.ConfigValues
import com.ks.scala.dbexplorer.routers.SearchUserRouter

import scala.io.StdIn

object WebService extends ConfigValues {

  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem = ActorSystem("actor-system")
    implicit val executionContextExecutor = system.dispatcher
    implicit val materializer = ActorMaterializer()

    object MainRouter extends SearchUserRouter {
      val routes = searchUserRoute
    }

    val errorHandler = ExceptionHandler {
      case exception => complete(StatusCodes.BadRequest, exception.toString)
    }
    def routes = handleExceptions(errorHandler) { MainRouter.routes }
    val bindingFuture = Http().bindAndHandle(routes, host, port)

    //Comment last lines out to run ~reStart
    println(
      s"Server online at " + host + ":" + port + "\nPress RETURN to stop..."
    )
    StdIn.readLine() // let it run until user presses return

  }
}
