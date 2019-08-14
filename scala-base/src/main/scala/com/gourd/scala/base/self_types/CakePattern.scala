package com.gourd.scala.base.self_types

/**
  * self-type 蛋糕模式（依赖注入）
  *
  * 本示例采用 WEB常见开发模式
  * controls 依赖 service
  * service 依赖 dao
  *
  * @author Li.Wei by 2019-08-14
  */
object CakePattern

// 数据层 dao
trait UserRepositoryComponent {
  protected val userRepository = new UserRepository

  class UserRepository {
    def authenticate(user: User): User = {
      println("authenticating user: " + user)
      user
    }

    def create(user: User): Unit = println("creating user: " + user)

    def delete(user: User): Unit = println("deleting user: " + user)
  }

}

// 服务层 service
trait UserServiceComponent {
  this: UserRepositoryComponent =>
  protected val userService = new UserService

  class UserService {
    def authenticate(username: String, password: String): User = null

    def create(username: String, password: String): Unit = {}

    def delete(user: User): Unit = userRepository.delete(user)
  }

}

// 控制层 controls
object ComponentRegistry extends
  UserServiceComponent with
  UserRepositoryComponent {
  override protected val userRepository = new UserRepository
  override protected val userService = new UserService
}