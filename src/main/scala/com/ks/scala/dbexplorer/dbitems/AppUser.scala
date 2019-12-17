package com.ks.scala.dbexplorer.dbitems

case class AppUser(id: Long,
                   creationTime: Long,
                   firstname: String,
                   lastname: String,
                   username: String,
                   password: String,
                   admin: Boolean)
object AppUser extends DBItem
