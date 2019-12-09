package com.ks.scala.dbexplorer.dbitems

case class AppUser(id: Long, creationTime: Long,
                   firstname: String,
                   lastname: String,
                   username: String,
                   password: String,
                   admin: Boolean,
                   session: UserSession)
object AppUser extends DBItem
