package com.ks.scala.dbexplorer.schema

import slick.ast.ColumnOption.AutoInc
import slick.lifted.{TableQuery, Tag}
import slick.model.Table

//case class AppUser(id: Option[Long], creationTime: Option[Long], firstname: String, lastname: String, username: String, password: String, admin: Boolean)

class AppUsers(tag: Tag) {
  /*extends Table[(Long, Long, String, String, String, String, Boolean)](
      tag,
      "app_users"
    ) {*/
  //val userSession = TableQuery[UserSessions]

  /*def id = column[Long]("id", O.PrimaryKey, AutoInc)
  def creationTime = column[Long]("creation_time")
  def firstname = column[String]("first_name")
  def lastname = column[String]("last_name")
  def username = column[String]("user_name")
  def password = column[String]("password")
  def admin = column[Boolean]("admin")
  //def session = foreignKey("user_id", deptId, departments)(_.id)
  def * = (id.?, creationTime, firstname, lastname, username, password, admin) <> (AppUser.tupled)*/

}

//case class Join(userId: Long, sessionId: Long)

/*
class JoinTable(tag: Tag) extends Table[(Long, Long)](tag, "appuser_sessions"){

  def userId = column[Long]("user_id")
  def sessionId = column[Long]("session_id")
  def * = (userId, sessionId) <> (Join.tupled)

}

//case class UserSession(id: Option[Long], createTime: Long, expirationTime: Long, token: String, page: String, stompId: String, ipAddress: String)

class UserSessions(tag: Tag) extends Table[(Long, Long, String, String, String, String, Boolean)](tag, "user_sessions") {

  def id = column[Long]("id", O.PrimaryKey, AutoInc)
  def createTime = column[Long]("start_time")
  def expirationTime = column[Long]("expiration_time")
  def token = column[String]("token")
  def page = column[String]("page")
  def stompId = column[String]("stomp_id")
  def ipAddress = column[String]("ip_address")
  def * = (id.?, createTim${user.e, expirationTime, token, page, stompId, ipAddress) <> (UserSession.tupled)
  }
}*/
