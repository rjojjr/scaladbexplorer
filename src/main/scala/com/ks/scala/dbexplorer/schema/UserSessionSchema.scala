package com.ks.scala.dbexplorer.schema

import com.ks.scala.dbexplorer.dbitems.{AppUser, UserSession}
import com.typesafe.config.ConfigFactory
import slick.jdbc.GetResult
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration._
import scala.concurrent.Await

class UserSessionSchema {

  val config = ConfigFactory.load()
  val db = Database.forConfig("mysql", config)

  implicit val sessionConverter =
    GetResult[UserSession](r => UserSession(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  def insertUserSession(session: UserSession, userId: String) = {
    val rawSQL =
      sqlu"insert into appuser_sessions (user_id, session_id, first_name, last_name, user_name, password, admin) values(${user.id}, ${user.creationTime}, ${user.firstname}, ${user.lastname}, ${user.username}, ${user.password}, ${user.admin})"
    Await.result(db.run(rawSQL), 5.seconds)
  }

  def getNextSessionId() = {
    val rawSQL =
      sql"""select ident_current('user_sessions')""".as[Long]
    Await.result(db.run(rawSQL), 5.seconds)
  }

  def getNextJoinId() = {
    val rawSQL =
      sql"""select ident_current('appuser_sessions')""".as[Long]
    Await.result(db.run(rawSQL), 5.seconds)
  }

}
