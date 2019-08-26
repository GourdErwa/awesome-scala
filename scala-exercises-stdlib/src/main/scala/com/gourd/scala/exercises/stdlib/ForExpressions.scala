package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** For表达式
  * https://www.scala-exercises.org/std_lib/for_expressions
  *
  * @author Li.Wei by 2019/8/22
  */
object ForExpressions {
  private val logger = LoggerFactory.getLogger("ForExpressions")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    val xValues = 1 to 4
    val yValues = 1 to 2
    val coordinates: Seq[(Int, Int)] = for {
      x <- xValues
      y <- yValues
    } yield (x, y)

    logger.info(s"coordinates=$coordinates") // Vector((1,1), (1,2), (2,1), (2,2), (3,1), (3,2), (4,1), (4,2))
    logger.info(s"coordinates(4)=${coordinates(4)}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { // 使用for我们可以使代码更具可读性：
    val nums = List(List(1), List(2), List(3), List(4), List(5))

    val result = for {
      numList <- nums
      num <- numList
      if num % 2 == 0
    } yield num

    logger.info(s"result=$result")
    // Which is the same as
    logger.info(s"nums.flatMap(numList => numList).filter(_ % 2 == 0)=${
      nums.flatMap(numList => numList).filter(_ % 2 == 0)
    }")
    // or the same as
    logger.info(s"nums.flatten.filter(_ % 2 == 0)=${
      nums.flatten.filter(_ % 2 == 0)
    }")
  }
}
