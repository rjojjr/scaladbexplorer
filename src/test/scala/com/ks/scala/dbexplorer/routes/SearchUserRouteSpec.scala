package com.ks.scala.dbexplorer.routes

import akka.actor.testkit.typed.scaladsl.ActorTestKit
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import akka.actor.typed.scaladsl.adapter._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.ks.scala.dbexplorer.restobjects.SearchUserRequest
import com.ks.scala.dbexplorer.routers.SearchUserRouter

class SearchUserRouteSpec
    extends WordSpec
    with Matchers
    with ScalaFutures
    with ScalatestRouteTest
    with SearchUserRouter {
  lazy val testKit = ActorTestKit()
  implicit def typedSystem = testKit.system
  override def createActorSystem(): akka.actor.ActorSystem =
    testKit.system.toClassic

  lazy val routes = searchUserRoute

  "SearchUserRouter" should {
    /*"return no users if no present (GET /users)" in {
      // note that there's no need for the host part in the uri:
      val request = HttpRequest(uri = "/users")

      request ~> routes ~> check {
        status should ===(StatusCodes.OK)

        // we expect the response to be json:
        contentType should ===(ContentTypes.`application/json`)

        // and no entries should be in the list:
        entityAs[String] should ===("""{"users":[]}""")
      }
    }*/

    "be able to find users (POST /users)" in {
      val userRequest = SearchUserRequest(
        "Xl6g0QqvOC7AsgPRwLZCoWn4lq4PK4s7JI9wmwQqgi8=",
        "admin"
      )
      val userEntity = Marshal(userRequest).to[MessageEntity].futureValue // futureValue is from ScalaFutures

      // using the RequestBuilding DSL:
      val request = Post("/search/user").withEntity(userEntity)

      request ~> routes ~> check {
        status should ===(StatusCodes.Created)

        // we expect the response to be json:
        contentType should ===(ContentTypes.`application/json`)

        // and we know what message we're expecting back:
        entityAs[String] should contain("""{"id":"0"""")
      }
    }
  }
}
