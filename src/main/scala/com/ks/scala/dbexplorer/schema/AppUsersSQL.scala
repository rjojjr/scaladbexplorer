package com.ks.scala.dbexplorer.schema

import slick.dbio.DBIO
import slick.jdbc.H2Profile.api._

class AppUsersSQL extends DBIOAction[Unit]{
  def insertAppUser(user: AppUser): DBIO[Int] =
    // Insert some suppliers
    sqlu"insert into app_users values(${user.id}, ${user.creationTime}, ${user.firstname}, ${user.lastname}, ${user.password}, ${user.admin})"

  def insertAppUsers(users: Seq[AppUser]) = {
    users.foreach(u => {
        insertAppUser(u)
    })
  }

  def deleteAppUser(user: AppUser) = {
    sql"""delete from app_users.u where u.id = ${user.id}"""
  }
}