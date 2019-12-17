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
    val joinId = getNextJoinId()(0)
    val rawJoinSQL =
      sqlu"insert into appuser_sessions (user_id, session_id, id) values(${userId}, ${session.id}, ${joinId})"
    Await.result(db.run(rawJoinSQL), 5.seconds)
    val rawSQL =
      sqlu"insert into user_sessions (id, start_time, expiration_time, token, page, stomp_id, ip_address) values(${session.id}, ${session.createTime}, ${session.expirationTime}, ${session.token}, ${session.page}, ${session.stompId}, ${session.ipAddress})"
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
