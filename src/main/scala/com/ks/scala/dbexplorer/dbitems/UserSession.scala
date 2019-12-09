package com.ks.scala.dbexplorer.dbitems

case class UserSession(id: Long,
                       createTime: Long,
                       expirationTime: Long,
                       token: String,
                       page: String,
                       stompId: String,
                       ipAddress: String)
object UserSession
