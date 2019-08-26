package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

import scala.language.postfixOps

/** 对象提取
  * https://www.scala-exercises.org/std_lib/extractors
  *
  * @author Li.Wei by 2019/8/23
  */
object Extractors {
  private val logger = LoggerFactory.getLogger("Extractors")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { // 创建案例类时，它可以自动与模式匹配一​​起使用，因为它有一个提取器
    case class Employee(firstName: String, lastName: String)

    val rob = Employee("Robin", "Williams")
    val result = rob match {
      case Employee("Robin", _) => "Where's Batman?"
      case _ => "No Batman Joke For You"
    }
    logger.info(s"result=$result")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { // 什么是提取器？在Scala中，它是任何object被调用的方法unapply，该方法用于通过返回包含在选项中的元组来反汇编给定的对象。提取器可用于分配值：
    class Car(val make: String, val model: String, val year: Short, val topSpeed: Short)

    object ChopShop {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
    }

    val ChopShop(a, b, c, d) = new Car("Chevy", "Camaro", 1978, 120)
    logger.info(s"(a, b, c, d)=${(a, b, c, d)}")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // 提取器也可用于模式匹配：
    class Car(val make: String, val model: String, val year: Short, val topSpeed: Short)

    object ChopShop {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)
    }

    val x = new Car("Chevy", "Camaro", 1978, 120) match {
      case ChopShop(s, t, u, v) => (s, t) // 等价于 case ChopShop(s, t, _, _) => (s, t)
      case _ => ("Ford", "Edsel")
    }
    logger.info(s"(x._1, x._2)=${(x._1, x._2)}")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { // 只要方法签名不相同，您就可以拥有任意数量的unapply方法：
    class Car(val make: String, val model: String, val year: Short, val topSpeed: Short)
    class Employee(val firstName: String, val middleName: Option[String], val lastName: String)

    object Tokenizer {
      def unapply(x: Car) = Some(x.make, x.model, x.year, x.topSpeed)

      def unapply(x: Employee) = Some(x.firstName, x.lastName)
    }

    val result = new Employee("Kurt", None, "Vonnegut") match {
      case Tokenizer(c, d) => "c: %s, d: %s".format(c, d)
      case _ => "Not found"
    }
    logger.info(s"result=$result")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { // 提取器可以是任何稳定对象，包括具有unapply方法的实例化类：
    class Car(val make: String, val model: String, val year: Short, val topSpeed: Short) {
      def unapply(x: Car) = Some(x.make, x.model)
    }

    val car = new Car("Chevy", "Camaro", 1978, 122)

    val result = car match {
      case car(make, model) => "make: %s, model: %s".format(make, model)
      case _ => "unknown"
    }
    logger.info(s"result=$result")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { // 通常在类的伴随对象中创建自定义提取器。
    class Employee(
                    val firstName: String,
                    val middleName: Option[String],
                    val lastName: String
                  )

    object Employee {
      // factory methods, extractors, apply
      // 提取器:创建表示对象的令牌
      def unapply(x: Employee) =
        Some(x.lastName, x.middleName, x.firstName)
    }

    val employee = new Employee("Singri", None, "Keerthi")
    val Employee(a, b, c) = employee

    logger.info(s"(a, b, c)=${(a, b, c)}")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { // 使用unapply模式匹配员工对象
    class Employee(val firstName: String,
                   val middleName: Option[String],
                   val lastName: String)
    object Employee {
      def unapply(x: Employee) = Some(x.lastName, x.middleName, x.firstName)
    }

    val employee = new Employee("Singri", None, "Keerthi")

    val result = employee match {
      case Employee("Singri", None, x) => "Yay, Singri %s! with no middle name!".format(x)
      case Employee("Singri", Some(x), _) => "Yay, Singri with a middle name of %s".format(x)
      case _ => "I don't care, going on break"
    }

    logger.info(s"result=$result")
  }

}
