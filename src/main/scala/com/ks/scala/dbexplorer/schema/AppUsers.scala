package com.ks.scala.dbexplorer.schema

import slick.ast.ColumnOption.AutoInc
import slick.lifted.{TableQuery, Tag}
import slick.model.Table

case class AppUser(id: Option[Long], creationTime: Option[Long], firstname: String, lastname: String, username: String, password: String, admin: Boolean)

class AppUsers(tag: Tag) extends Table[(Long, Long, String, String, String, String, Boolean)](tag, "app_users") {
  val userSession = TableQuery[UserSessions]

  def id = column[Long]("id", O.PrimaryKey, AutoInc)
  def creationTime = column[Long]("creation_time")
  def firstname = column[String]("first_name")
  def lastname = column[String]("last_name")
  def username = column[String]("user_name")
  def password = column[String]("password")
  def admin = column[Boolean]("admin")
  def session = foreignKey("user_id", deptId, departments)(_.id)
}

class JoinTable(tag: Tag) extends Table[(Long, Long)](tag, "appuser_sessions"){
  def userId = column[Long]("user_id")
  def sessionId = column[Long]("session_id")
}
