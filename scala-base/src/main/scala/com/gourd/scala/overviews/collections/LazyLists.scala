package com.gourd.scala.overviews.collections

import org.slf4j.LoggerFactory

/**
  * @author Li.Wei by 2019/8/28
  */
object LazyLists {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    var rec = 0

    def streamRange(lo: Int, hi: Int): LazyList[Int] = {
      rec = rec + 1
      if (lo >= hi) LazyList.empty
      else LazyList.cons(lo, streamRange(lo + 1, hi))
    }

    val list = streamRange(1, 10).take(3).toList
    logger.info(s"list=$list")
    logger.info(s"rec=$rec")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { //
    val builder = new StringBuilder

    val x = { // 参数变量、初始化时执行一次
      val builder1 = builder += 'x'
      1
    }
    lazy val y = { // 参数变量、初始化时执行一次（延迟执行）
      val builder1 = builder += 'y'
      2
    }

    def z = { // 方法、每次调用执行
      val builder1 = builder += 'z'
      3
    }

    logger.info(s"(z + y + x + z + y + x)=${z + y + x + z + y + x}")
    logger.info(s"builder=$builder") // xzyz
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { //

  }

  logger.info("------------------------> block line [4] <----------------------------")

  { //
  }
}
