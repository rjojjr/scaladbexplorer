package com.ks.scala.dbexplorer.dbobjects

import com.ks.scala.dbexplorer.dbitems.AppUser
import com.ks.scala.dbexplorer.schema.AppUsersSchema
import org.scalatest.{FlatSpec, Matchers, WordSpecLike}

import com.ks.scala.dbexplorer.dbobjects.TestDB._

class TestDBSpec extends WordSpecLike with Matchers {

  val time = System.currentTimeMillis()
  val users = Seq(AppUser(1, time, "test", "user1", "testuser1", "SomePW55#", false), AppUser(2, time, "test", "user2", "testuser2", "SomePW55#", false), AppUser(0, 0, null, null, null, null, false))

  "TestDB" should {
    def test1(appUser: AppUser) = {
      create (appUser)
      val result = (findByUsername (appUser.username))
      delete (appUser)
      result should equal(appUser)
    }

    def test2(appUser: AppUser) = {
      create (appUser)
      val result = (findByUsernameAndPassword (appUser.username, appUser.password))
      delete (appUser)
      result should equal(appUser)
    }

    def test3(appUsers: Seq[AppUser]) = {
      val result = (findByUsernameAndPassword (appUsers(0).username, appUsers(0).password))
      result should equal(appUsers(2))
    }

    def test4(appUsers: Seq[AppUser]) = {
      create (appUsers(0))
      delete (appUsers(0))
      val result = (findByUsernameAndPassword (appUsers(0).username, appUsers(0).password))
      result should equal(appUsers(2))
    }

    def test5(appUsers: Seq[AppUser]) = {
      var found = true;
      val users: Seq[AppUser] = appUsers.filter(_.id != 0)
      create (users)
      users.foreach(u => {
        if (!u.equals(findByUsernameAndPassword (u.username, u.password))){
          found = false
        }
        delete(u)
      })
      found should be (true)
    }

    def test6(appUsers: Seq[AppUser]) = {
      var found = false;
      val users: Seq[AppUser] = appUsers.filter(_.id != 0)
      create (users)
      delete(users)
      users.foreach(u => {
        if (u.equals(findByUsernameAndPassword (u.username, u.password))){
          found = true
        }
      })
      found should be (false)
    }

    "find inserted user by username" in test1(users(0))

    "find inserted user by username and password" in test2(users(0))

    "not find un-inserted user by username and password" in test3(users)

    "not find deleted user by username and password" in test4(users)

    "find inserted users by username and password" in test5(users)

    "not find deleted users by username and password" in test6(users)
  }
}
