package com.ks.scala.dbexplorer.schema

import com.ks.scala.dbexplorer.dbitems.UserSession
import slick.dbio.{DBIO, DBIOAction}

class UserSessionSQL extends DBIOAction[Unit] {
  def insertUserSession(session: UserSession): DBIO[Int] =
    sqlu"insert into user_sessions values(${session.id}, ${session.createTime}, ${session.expirationTime}, ${session.token}, ${session.page}, ${session.stompId}, ${session.ipAddress})"

  def deleteUserSession(session: UserSession) = {
    sql"""delete from user_sessions.s where s.id = ${session.id}"""
  }
}
