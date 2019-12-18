package com.ks.scala.dbexplorer.config

import com.typesafe.config.ConfigFactory

trait ConfigValues {
  implicit val config = ConfigFactory.load()
  val search_user_endpoint = config.getString("endpoints.search_user_records")
  val token = config.getString("server.token")
  val host = config.getString("server.host")
  val port = config.getInt("server.port")
}
