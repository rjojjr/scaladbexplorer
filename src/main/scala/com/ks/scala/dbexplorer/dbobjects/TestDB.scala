package com.ks.scala.dbexplorer.dbobjects

import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.schema.AppUsersSQL
import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

object TestDB {

  def create(user: AppUser) = {
    val time = System.currentTimeMillis()
    val sql = new AppUsersSQL()
    sql insertAppUser user
    println("User " + user.username + " inserted at " + time)
  }

  def find(userName: String) = {
    val time = System.currentTimeMillis()
    val sql = new AppUsersSQL()
    val sqluser = sql getUserByUsername userName
    val vector = sqluser
    if(vector.length == 0){
      var appUser: AppUser = new AppUser(0, 0, null, null, null, null, false);
      println("User " + userName + " not found at " + time)
      appUser
    } else {
      var appUser: AppUser = vector(0)
      println("User " + appUser.username + " found at " + time)
      appUser
    }
  }

  def delete(appUser: AppUser) = {
    val sql = new AppUsersSQL()
    sql deleteAppUser(appUser)
  }

  /*def main(args: Array[String]): Unit ={
    val time = System.currentTimeMillis()
    val user = AppUser(1, time, "test", "user", "testuser1", "SomePW55#", false)
    create(user)
    find(user.username)
    delete(user)
  }*/

}
