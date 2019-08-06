package com.gourd.scala

import org.slf4j.LoggerFactory

/**
  * https://books.underscore.io/shapeless-guide/shapeless-guide.html
  *
  * @author Li.Wei by 2019-08-06
  */
object ShapelessApp {
  private val logger = LoggerFactory.getLogger("ShapelessApp")

  def main(args: Array[String]): Unit = {
    logger.info("hello")
  }

  case class Employee(name: String, number: Int, manager: Boolean)

  case class IceCream(name: String, numCherries: Int, inCone: Boolean)

  def employeeCsv(e: Employee): List[String] = List(e.name, e.number.toString, e.manager.toString)

  def iceCreamCsv(c: IceCream): List[String] = List(c.name, c.numCherries.toString, c.inCone.toString)

  import shapeless._

  val genericEmployee = Generic[Employee].to(Employee("Dave", 123, manager = false))
  // genericEmployee: String :: Int :: Boolean :: shapeless.HNil = Dave :: 123 :: false :: HNil

  val genericIceCream = Generic[IceCream].to(IceCream("Sundae", 1, inCone = false))
  // genericIceCream: String :: Int :: Boolean :: shapeless.HNil = Sundae :: 1 :: false :: HNil

  // def genericCsv(gen: String :: Int :: Boolean :: HNil): List[String] =
    // List(gen(0), gen(1).toString, gen(2).toString)

  // genericCsv(genericEmployee)
  // res2: List[String] = List(Dave, 123, false)

  // genericCsv(genericIceCream)
  // res3: List[String] = List(Sundae, 1, false)

}