package com.ks.scala.dbexplorer.schema

import slick.ast.ColumnOption.AutoInc
import slick.lifted.Tag
import slick.model.Table

case class UserLog(id: Option[Long], action: String, time: Long)

class UserLogs(tag: Tag) extends Table[(Long, String, Long)](tag, "user_logs") {

  def id = column[Long]("id", O.PrimaryKey, AutoInc)
  def action = column[String]("action")
  def time = column[Long]("time")
  def * = (id.?, action, time) <> (UserSession.tupled)

}
