package com.gourd.scala.cats

import cats.Show
import org.slf4j.LoggerFactory

/** [[Show]]
  *
  * @author Li.Wei by 2019-08-06
  */
object ExampleShow {

  private val logger = LoggerFactory.getLogger("ExampleShow")

  def main(args: Array[String]): Unit = {
    logger.info(s"java toString:${new {}.toString}") // res0: String = $anon$1@8932960

    import cats.implicits._
    {
      case class Person(name: String, age: Int)
      // defined class Person
      implicit val showPerson: Show[Person] = Show.show(person => person.name)
      logger.info(Person("John", 31).show) // John
    }
    {
      case class Department(id: Int, name: String)
      implicit val showDep: Show[Department] = Show.fromToString
      logger.info(Department(100, "John").show) // Department(100,John)
    }
  }

}