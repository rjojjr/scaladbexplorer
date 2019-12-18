package com.ks.scala.dbexplorer.routers

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import com.ks.scala.dbexplorer.config.ConfigValues
import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.restobjects.{RejectToken, SearchUserRequest}
import com.ks.scala.dbexplorer.dbobjects.TestAppUserDB._

import scala.concurrent.ExecutionContext

trait SearchUserRouter extends ConfigValues {
  def searchUserRoute(implicit ec: ExecutionContext, ac: ActorSystem) =
    path(search_user_endpoint) {

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
