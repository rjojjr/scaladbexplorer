package com.ks.scala.dbexplorer.schema

import akka.actor.ActorSystem
import com.ks.scala.dbexplorer.dbitems.AppUser
import com.typesafe.config.ConfigFactory
import slick.dbio.DBIO
import slick.jdbc.GetResult
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

class AppUsersSQL {

  val config = ConfigFactory.load()
  val db = Database.forConfig("mysql", config)

  implicit val userConverter =
    GetResult[AppUser](r => AppUser(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  def insertAppUser(user: AppUser) = {
    val rawSQL =
      sqlu"insert into app_users (id, creation_time, first_name, last_name, user_name, password, admin) values(${user.id}, ${user.creationTime}, ${user.firstname}, ${user.lastname}, ${user.username}, ${user.password}, ${user.admin})"
    Await.result(db.run(rawSQL), 20.seconds)
  }
  def insertAppUsers(users: Seq[AppUser]) = {
    users.foreach(u => {
      insertAppUser(u)
    })
  }

  def deleteAppUser(user: AppUser) = {
    sql"""delete from app_users u where u.id = ${user.id}"""
  }

  def getUserSessionId(user: AppUser) = {
    /* sql"""select s.session_id from appuser_sessions s where s.user_id = ${user.id}"""
      .as[(Long)]*/
  }

  def getUserByUsername(userName: String) = {
    val rawSQL =
    sql"""select u.id, u.creation_time, u.first_name, u.last_name, u.user_name, u.password, u.admin from app_users u where u.user_name = ${userName}"""
      .as[AppUser]
    Await.result(db.run(rawSQL), 20.seconds)

  }

  def getUserByUsernameAndPassword(userName: String, password: String)(implicit ec: ExecutionContext, ac: ActorSystem) = {
    val rawSQL =
    sql"""select u.id, u.creation_time, u.first_name, u.last_name, u.user_name, u.password, u.admin from app_users u where u.user_name = ${userName} and u.password = $password}"""
      .as[AppUser]
    val result = db.run(rawSQL)
  }

}
