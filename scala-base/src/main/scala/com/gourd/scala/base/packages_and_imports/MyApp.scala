package com.gourd.scala.base.packages_and_imports

import org.slf4j.LoggerFactory

/** 包和导入
  * Scala 使用包来创建命名空间，从而允许你创建模块化程序。
  *
  * 注意：包 scala 和 java.lang 以及 object Predef 是默认导入的。
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  {
    //  import users._  // 导入包 users 中的所有成员
    //  import users.User  // 导入类 User
    //  import users.{User, UserPreferences}  // 仅导入选择的成员
    //  import users.{UserPreferences => UPrefs}  // 导入类并且设置别名

    // Scala 不同于 Java 的一点是 Scala 可以在任何地方使用导入：
    def sqrtPlus(x: Int) = {
      import scala.math.sqrt
      sqrt(x) + 1.0
    }

  }
}
