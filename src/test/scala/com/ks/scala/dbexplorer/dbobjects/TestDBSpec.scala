package com.ks.scala.dbexplorer.dbobjects

import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.schema.AppUsersSQL
import org.scalatest.{FlatSpec, Matchers, WordSpecLike}

import com.ks.scala.dbexplorer.dbobjects.TestDB._

class TestDBSpec extends WordSpecLike with Matchers {

  val time = System.currentTimeMillis()
  val users = Seq(AppUser(1, time, "test", "user1", "testuser1", "SomePW55#", false), AppUser(2, time, "test", "user2", "testuser2", "SomePW55#", false))

  "DBExplorer" should {
    def test1(appUser: AppUser) = {
      create (appUser)
      val result = (find (appUser.username))
      delete (appUser)
      result should equal(appUser)
    }

    /*def test2(appUser: AppUser) = {
      val sql = new AppUsersSQL()
      create (appUser)
      val result = (sql getUserByUsernameAndPassword (appUser.username, appUser.password)) (0)
      sql deleteAppUser appUser
      result should equal(appUser)
    }*/

    "find inserted user by username" in test1(users(0))
  }
}
