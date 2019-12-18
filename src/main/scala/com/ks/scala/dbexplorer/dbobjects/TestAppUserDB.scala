package com.ks.scala.dbexplorer.dbobjects

import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.schema.AppUsersSchema

import com.kirchnersolutions.utilities.CryptTools

object TestAppUserDB {

  def create(user: AppUser) = {
    val time = System.currentTimeMillis()
    val sql = new AppUsersSchema()
    sql insertAppUser user
  }

  def create(users: Seq[AppUser]) = {
    val time = System.currentTimeMillis()
    val sql = new AppUsersSchema()
    sql insertAppUsers users
  }

  def delete(appUser: AppUser) = {
    val sql = new AppUsersSchema()
    sql deleteAppUser (appUser)
  }

  def delete(appUsers: Seq[AppUser]) = {
    val sql = new AppUsersSchema()
    sql deleteAppUsers (appUsers)
  }

  def findByUsername(userName: String): AppUser = {
    val time = System.currentTimeMillis()
    val sql = new AppUsersSchema()
    val sqluser = sql getUserByUsername userName
    val vector = sqluser
    if (vector.length == 0) {
      var appUser: AppUser = new AppUser(0, 0, null, null, null, null, false);
      println("User " + userName + " not found at " + time)
      appUser
    } else {
      var appUser: AppUser = vector(0)
      appUser
    }
  }

  def findByUsernameAndPassword(userName: String, password: String) = {
    val time = System.currentTimeMillis()
    val sql = new AppUsersSchema()
    val sqluser = sql getUserByUsernameAndPassword (userName, password)
    val vector = sqluser
    if (vector.length == 0) {
      var appUser: AppUser = new AppUser(0, 0, null, null, null, null, false);
      appUser
    } else {
      var appUser: AppUser = vector(0)
      appUser
    }
  }

  def findByUsernameAndEncodedPassword(userName: String, password: String) = {
    val time = System.currentTimeMillis()
    val pword = CryptTools generateEncodedSHA256Password password
    val sql = new AppUsersSchema()
    val sqluser = sql getUserByUsernameAndPassword (userName, pword)
    val vector = sqluser
    if (vector.length == 0) {
      var appUser: AppUser = new AppUser(0, 0, null, null, null, null, false);
      appUser
    } else {
      var appUser: AppUser = vector(0)
      appUser
    }
  }

}
