package com.ks.scala.dbexplorer.dbobjects

import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.schema.AppUsersSQL

object TestDB {

  def create() {
    val time = System.currentTimeMillis()
    val user = AppUser(1, time, "test", "user", "testuser1", "SomePW55#", false)
    val sql = new AppUsersSQL()
    sql insertAppUser (user)
    println("User " += user.username += " inserted at " + time)
  }

  def find() {
    val time = System.currentTimeMillis()
    val sql = new AppUsersSQL()
    val sqluser = sql getUserByUsername ("testuser1")
    val appUser: AppUser = sqluser.asInstanceOf[AppUser]
    println("User " += appUser.username += " found at " + time)
  }

}
