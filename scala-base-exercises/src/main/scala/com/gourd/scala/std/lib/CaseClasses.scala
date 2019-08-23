package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/** case classes 案例类
  * https://www.scala-exercises.org/std_lib/case_classes
  *
  * @author Li.Wei by 2019-08-21
  */
object CaseClasses {
  private val logger = LoggerFactory.getLogger("CaseClasses")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    case class Person(first: String, last: String)

    val p1 = Person("Fred", "Jones")
    val p2 = Person("Shaggy", "Rogers")
    val p3 = Person("Fred", "Jones")

    logger.info(s"(p1 == p2)=${p1 == p2}")
    logger.info(s"(p2 == p3)=${p2 == p3}")

    logger.info(s"(p1 eq p2)=${p1 eq p2}")
    logger.info(s"(p2 eq p3)=${p2 eq p3}")

    logger.info(s"(p1.hashCode() == p2.hashCode())=${p1.hashCode() == p2.hashCode()}")
    logger.info(s"(p2.hashCode() == p3.hashCode())=${p2.hashCode() == p3.hashCode()}")

    logger.info(s"(p1.toString)=${p1.toString}")

    logger.info(s"p1.isInstanceOf[Serializable]=${p1.isInstanceOf[Serializable]}")

    // this seems weird, but it's critical to other features of Scala
    logger.info(s"Person.unapply(p1).get=${Person.unapply(p1).get}")

  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    case class Person(first: String, last: String, age: Int = 0, ssn: String = "")
    val p1 = Person("Fred", "Jones", 23, "111-22-3333")
    val p2 = Person("Samantha", "Jones") // note missing age and ssn
    val p3 = Person(last = "Jones", first = "Fred", ssn = "111-22-3333") // note the order can change, and missing age
    val p4 = p3.copy(age = 23)

    // 分别指出 p1/p2/p3/p4 四个成员变量值
    // (p1 == p4) ？
    logger.info(s"p1=$p1")
    logger.info(s"p2=$p2")
    logger.info(s"p3=$p3")
    logger.info(s"p4=$p4")
    logger.info(s"p1 == p4=${p1 == p4}")
  }
}
