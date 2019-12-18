package com.ks.scala.dbexplorer.restobjects

case class SearchUserRequest(token: String, username: String)

object SearchUserRequest extends RestObject
