package com.ks.scala.dbexplorer.dbobjects

import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.schema.AppUsersSQL
import org.scalatest.{FlatSpec, Matchers, WordSpecLike}

import slick.jdbc._

class TestDBSpec extends WordSpecLike with Matchers {

  val time = System.currentTimeMillis()
  val user = AppUser(1, time, "test", "user", "testuser1", "SomePW55#", false)

  "DBExplorer" should {
    def testSenerio(appUser: AppUser) = {
      val sql = new AppUsersSQL()
      sql insertAppUser (appUser)
      val result = sql getUserByUsername (appUser.username)
      result should equal(appUser)
    }

    "find inserted user" in testSenerio(user)
  }
}
