package com.gourd.scala.cats

import cats.data.NonEmptyList
import org.slf4j.LoggerFactory

/**
  * https://typelevel.org/cats/typeclasses/comonad.html
  *
  * @author Li.Wei by 2019-08-06
  */
object ComonadExample {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}
  {
    import cats.implicits._
    NonEmptyList.of(1, 2, 3).extract

  }

}