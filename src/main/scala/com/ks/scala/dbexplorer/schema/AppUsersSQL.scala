package com.ks.scala.dbexplorer.schema

import com.ks.scala.dbexplorer.dbitems.AppUser
import slick.dbio.DBIO
import slick.jdbc.GetResult
import slick.jdbc.H2Profile.api._

object AppUsersSQL extends DBIOAction[Unit]{

  implicit val userConverter:AppUser = GetResult[AppUser](r => AppUser(r.<<[Long], r.<<[Long], r.<<[String], r.<<[String], r.<<[String], r.<<[String], r.<<[String]))

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
    sql"""select s.session_id from appuser_sessions s where s.user_id = ${user.id}"""as[(Long)]
  }

  def getUserByUsername(userName: String) = {

    sql"""select u.user_id, u.creation_time, u.first_name, u.last_name, u.username, u.password, u.admin from app_users u where u.user_name = ${userName}""".as[AppUser]

  }

  def getUserByUsernameAndPassword(userName: String, password: String) = {
    sql"""select u.user_id, u.creation_time, u.first_name, u.last_name, u.username, u.password, u.admin from app_users u where u.user_name = ${userName} and u.password = $password}"""as[AppUser]
  }

}