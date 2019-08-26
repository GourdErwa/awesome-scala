package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** 对象
  *
  * @author Li.Wei by 2019-08-06
  */
object Objects {
  private val logger = LoggerFactory.getLogger("ExampleOption")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    object Greeting {
      def english = "Hi"

      def espanol = "Hola"
    }

    val x = Greeting
    val y = x

    //Reminder: eq checks for reference
    logger.info(s"x eq y=${x eq y}")
    logger.info(s"x == y=${x == y}")
    val z = Greeting
    logger.info(s"x eq z=${x eq z}")
    logger.info(s"x == z=${x == z}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    class Movie(val name: String, val year: Short)

    object Movie {
      def academyAwardBestMoviesForYear(x: Short) = {
        //This is a match statement, more powerful than a Java switch statement!
        x match {
          case 1930 => Some(new Movie("All Quiet On the Western Front", 1930))
          case 1931 => Some(new Movie("Cimarron", 1931))
          case 1932 => Some(new Movie("Grand Hotel", 1932))
          case _ => None
        }
      }
    }
    val name = Movie.academyAwardBestMoviesForYear(1932).get.name
    logger.info(s"Movie.academyAwardBestMoviesForYear(1932).get.name=$name")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  {
    class Person(val name: String, private val superheroName: String) //The superhero name is private!

    object Person {
      def showMeInnerSecret(x: Person) = x.superheroName
    }

    val clark = new Person("Clark Kent", "Superman")
    val peter = new Person("Peter Parker", "Spider-Man")

    logger.info(s"Person.showMeInnerSecret(clark)=${Person.showMeInnerSecret(clark)}")
    logger.info(s"Person.showMeInnerSecret(peter)=${Person.showMeInnerSecret(peter)}")
  }

}
