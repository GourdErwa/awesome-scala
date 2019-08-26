package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** 高阶函数
  *
  * @author Li.Wei by 2019-08-21
  */
object HigherOrderFunctions {
  private val logger = LoggerFactory.getLogger("ExampleOption")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    def lambda = { x: Int =>
      x + 1
    }

    def lambda2 = (x: Int) => x + 2

    val lambda3 = (x: Int) => x + 3

    val lambda4 = new Function1[Int, Int] {
      def apply(v1: Int): Int = v1 - 1
    }

    def lambda5(x: Int) = x + 1

    val result = lambda(3)
    val `result1andhalf` = lambda.apply(3)

    val result2 = lambda2(3)
    val result3 = lambda3(3)
    val result4 = lambda4(3)
    val result5 = lambda5(3)

    //Reminder: eq checks for reference
    logger.info(s"result=$result")
    logger.info(s"result1andhalf=$result1andhalf")
    logger.info(s"result2=$result2")
    logger.info(s"result3=$result3")
    logger.info(s"result4=$result4")
    logger.info(s"result5=$result5")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    var incrementer = 1

    def closure = { x: Int =>
      x + incrementer
    }

    val result1 = closure(10)
    logger.info(s"result1=$result1")

    incrementer = 2

    val result2 = closure(10)
    logger.info(s"result2=$result2")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  {
    def summation(x: Int, y: Int => Int) = y(x)

    var incrementer = 3

    def closure = (x: Int) => x + incrementer

    val result = summation(10, closure)
    logger.info(s"result1=$result")

    incrementer = 4
    val result2 = summation(10, closure)
    logger.info(s"result2=$result2")
  }

  logger.info("------------------------> block line [4 <----------------------------")

  {
    // emphasis
    def addWithSyntaxSugar(x: Int) = (y: Int) => x + y

    // isInstanceOf与instanceof java中的相同，但在这种情况下，参数类型可以使用具有单个下划线的存在类型* *消隐，因为参数类型在运行时是未知的。
    val isInstanceOf = addWithSyntaxSugar(1).isInstanceOf[Function[_, _]]
    logger.info(s"isInstanceOf=$isInstanceOf")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  {
    def makeUpper(xs: List[String]) = xs map (_.toUpperCase)


    def makeWhatEverYouLike(xs: List[String], sideEffect: String => String) = xs map sideEffect

    logger.info(s"""makeUpper(List("abc", "xyz", "123s"))=${makeUpper(List("abc", "xyz", "123"))}""")

    logger.info(
      s"""makeWhatEverYouLike(List("ABC", "XYZ", "123"), _.toLowerCase)=${
        makeWhatEverYouLike(List("ABC", "XYZ", "123"), _.toLowerCase)
      }""")

    //using it inline
    val myName = (name: String) => s"My name is $name"

    logger.info(s"""makeWhatEverYouLike(List("John", "Mark"), myName)=${makeWhatEverYouLike(List("John", "Mark"), myName)}""")
    logger.info(s"""List("Scala", "Erlang", "Clojure") map (_.length)=${List("Scala", "Erlang", "Clojure") map (_.length)}""")
  }
}
