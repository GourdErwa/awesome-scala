package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

import scala.language.postfixOps

/** SequencesAndArrays
  * [[Seq]][[Array]]
  *
  * @author Li.Wei by 2019/8/22
  */
object SequencesAndArrays {
  private val logger = LoggerFactory.getLogger("SequencesAndArrays")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    val l = List(1, 2, 3)
    val a = l.toArray
    logger.info(s"a=$a")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { //
    val a = Array(1, 2, 3)
    val s = a.toSeq
    val l = s.toList
    logger.info(s"l=$l")

    val s1 = for (v <- 1 to 4) yield v
    logger.info(s"s1=$s1")

    val s2 = for (v <- 1 to 10 if v % 3 == 0) yield v
    logger.info(s"s2=$s2")

    val filtered = Seq("hello", "to", "you").filter(_.length > 2)
    logger.info(s"filtered=$filtered")

    val r: Seq[String] = Seq("hello", "world") map {
      _.reverse
    }
    logger.info(s"r=$r")
  }

}
