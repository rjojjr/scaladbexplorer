package com.ks.scala.dbexplorer.schema

import slick.ast.ColumnOption.AutoInc
import slick.lifted.Tag
import slick.model.Table

case class Reading(id: Option[Long], readingTime: Long, temperatire: Int, humidity: Int, roomname: String)

class Readings(tag: Tag) extends Table[(Long, Long, Int, Int, String)](tag, "readings") {

  def id = column[Long]("id", O.PrimaryKey, AutoInc)
  def readingTime = column[Long]("reading_time")
  def temperature = column[Int]("temperature")
  def humidity = column[Int]("humidity")
  def roomname = column[String]("room_name")
  def * = (id.?, readingTime, temperature, humidity, roomname) <> (Reading.tupled)

}
