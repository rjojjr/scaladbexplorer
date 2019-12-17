package com.ks.scala.dbexplorer

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.ExceptionHandler
import akka.stream.ActorMaterializer

import scala.io.StdIn

object WebService {

  /*def main(args: Array[String]): Unit ={

    implicit val system: ActorSystem = ActorSystem("actor-system")  // ActorMaterializer requires an implicit ActorSystem
    implicit val executionContextExecutor = system.dispatcher  // bindingFuture.map requires an implicit ExecutionContext
    implicit val materializer = ActorMaterializer()  // bindAndHandle requires an implicit materializer

    /*object MainRouter extends LoginRouter with LogoutRouter with SummaryRouter {
      val routes = loginRoute ~ logoutRoute ~ summaryRoute
    }

    val errorHandler = ExceptionHandler { case exception => complete(StatusCodes.BadRequest, exception.toString) }
    def routes = handleExceptions(errorHandler) { MainRouter.routes }
    val bindingFuture = Http().bindAndHandle(routes, host, port)

    //Comment last lines out to run ~reStart*/
    //println(s"Server online at " + host + ":" + port + "\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return


  }*/
}
