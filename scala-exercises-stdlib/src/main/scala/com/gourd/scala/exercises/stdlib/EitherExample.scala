package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

import scala.util.Either

/**
  * [[Either]]
  *
  * @author Li.Wei by 2019/8/28
  */
object EitherExample {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    def triple(x: Int): Int = 3 * x

    def tripleEither(x: Either[String, Int]): Either[String, Int] = x.map(triple)

    logger.info(s"tripleEither(Right(1))=${tripleEither(Right(1))}")
    logger.info(s"""tripleEither(Left(""not a number""))=${tripleEither(Left("not a number"))}""")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { //
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { //
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { //
  }
}
