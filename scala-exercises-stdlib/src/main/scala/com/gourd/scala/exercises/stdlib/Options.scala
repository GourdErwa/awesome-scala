package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** [[Option]]
  *
  * @author Li.Wei by 2019-08-06
  */
object Options {
  private val logger = LoggerFactory.getLogger("Options")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    val someValue: Option[Double] = Some(20.0)
    val value = someValue match {
      case Some(v) => v
      case None => 0.0
    }
    val noValue: Option[Double] = None
    val value1 = noValue match {
      case Some(v) => v
      case None => 0.0
    }
    logger.info(s"value=$value")
    logger.info(s"value1=$value1")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    val number = Some(3)
    val noNumber: Option[Int] = None
    logger.info(s"number.map(_ * 1.5)=${number.map(_ * 1.5)}")
    logger.info(s"noNumber.map(_ * 1.5)=${noNumber.map(_ * 1.5)}")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  {
    val number = Some(5)
    val noNumber: Option[Int] = None
    logger.info(s"number.fold(1)(_ * 3)=${number.fold(1)(_ * 3)}")
    logger.info(s"noNumber.fold(1)(_ * 3)=${noNumber.fold(1)(_ * 3)}")
  }

}
