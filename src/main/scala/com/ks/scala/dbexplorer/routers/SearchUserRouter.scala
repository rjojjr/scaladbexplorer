package com.ks.scala.dbexplorer.routers

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{
  as,
  complete,
  entity,
  path,
  post,
  pathEnd,
  pathPrefix,
  concat
}
import com.ks.scala.dbexplorer.config.ConfigValues
import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.restobjects.{RejectToken, SearchUserRequest}
import com.ks.scala.dbexplorer.dbobjects.TestAppUserDB._

import scala.concurrent.ExecutionContext

trait SearchUserRouter extends ConfigValues {

  def searchUserRoute(implicit ec: ExecutionContext, ac: ActorSystem) =
    pathPrefix("search") {

      concat {
        pathEnd {
          complete(new RejectToken("Invalid path"))
        }
        path("user") {
          post {
            entity(as[SearchUserRequest]) { searchUserRequest =>
              if (searchUserRequest.token == token) {
                val res = findByUsername(searchUserRequest.username)
                complete(res)
              } else {
                complete(new RejectToken("Invalid token"))
              }
            }
          }
        }
      }

    }
}
