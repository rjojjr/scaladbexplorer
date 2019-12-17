package com.ks.scala.dbexplorer.schema

import com.ks.scala.dbexplorer.dbitems.AppUser
import com.typesafe.config.ConfigFactory
import slick.dbio.DBIO
import slick.jdbc.GetResult
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

class AppUsersSQL {

  val config = ConfigFactory.load()
  val db = Database.forConfig("mysql", config)

  implicit val userConverter =
    GetResult[AppUser](r => AppUser(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  def insertAppUser(user: AppUser): DBIO[Int] =
    // Insert some suppliers
    sqlu"insert into app_users values(${user.id}, ${user.creationTime}, ${user.firstname}, ${user.lastname}, ${user.password}, ${user.admin})"

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
    //val rawSQL =
    sql"""select u.id, u.creation_time, u.first_name, u.last_name, u.user_name, u.password, u.admin from app_users u where u.user_name = ${userName}"""
      .as[AppUser]
    //Await.result(db.run(rawSQL), 30000.millis).asInstanceOf[AppUser]
    implicit val userConverter =
      GetResult[AppUser](r => AppUser(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))
  }

  def getUserByUsernameAndPassword(userName: String, password: String) = {
    //val rawSQL =
    sql"""select u.id, u.creation_time, u.first_name, u.last_name, u.user_name, u.password, u.admin from app_users u where u.user_name = ${userName} and u.password = $password}"""
      .as[AppUser]
    //Await.result(db.run(rawSQL), 30000.millis).asInstanceOf[AppUser]
    implicit val userConverter =
      GetResult[AppUser](r => AppUser(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))
  }

}
