package com.gourd.scala
package cats

import org.slf4j.LoggerFactory

/**
  * https://typelevel.org/cats/
  *
  * @author Li.Wei by 2019-08-06
  */
object CatsApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {
    logger.info("hello")
  }

}