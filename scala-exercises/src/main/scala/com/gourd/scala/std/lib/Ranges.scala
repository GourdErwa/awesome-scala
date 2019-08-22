package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/** Ranges
  * https://www.scala-exercises.org/std_lib/Ranges
  *
  * 有序的整数序列，它们间隔相等。例如，“1,2,3”是一个范围，“5,8,11,14”也是如此。
  * 要创建Scala中的一个范围，使用预定义的方法 to，until 和 by。
  *
  * 1 to 3 生成 “1,2,3”             类似于Range(1, 3).inclusive
  * 5 to 14 by 3 生成 “5,8,11,14”   类似于Range(5, 14).by(3).inclusive
  *
  * 如果要创建一个不包括其上限的范围，则使用 until 而不是 to。e.g: 1 until 3生成“1,2”。
  * 注意 Range(a, b, c) 、 a until b by c 相同
  *
  * 范围以恒定空间表示，因为它们只能由三个数字定义：它们的开始，结束和步进值。由于这种表示，大多数对范围的操作都非常快。
  *
  * @author Li.Wei by 2019-08-21
  */
object Ranges {
  private val logger = LoggerFactory.getLogger("Ranges")

  def main(args: Array[String]): Unit = {
    logger.info("------------------------> block line [1] <----------------------------")

    {
      val someNumbers = Range(0, 10) // 0 1 2 3 4 5 6 7 8 9
      val second = someNumbers(1)
      val last = someNumbers.last
      logger.info(s"someNumbers=$someNumbers")
      logger.info(s"someNumbers.size=${someNumbers.size}")
      logger.info(s"second=$second")
      logger.info(s"last=$last")

      logger.info(s"(0 until 10) == someNumbers=${(0 until 10) == someNumbers}")
    }

    logger.info("------------------------> block line [2] <----------------------------")

    {
      // 指定 增量(by)
      val someNumbers = Range(2, 10, 3) // 2 5 8
      val second = someNumbers(1)
      val last = someNumbers.last
      logger.info(s"someNumbers=$someNumbers")
      logger.info(s"someNumbers.size=${someNumbers.size}")
      logger.info(s"second=$second")
      logger.info(s"last=$last")
      logger.info(s"(0 until 10 by 3) == someNumbers=${(0 until 10 by 3) == someNumbers}")
    }

    logger.info("------------------------> block line [3] <----------------------------")

    { // 即使以步进增量，范围也不包括其上限：
      val someNumbers = Range(0, 34, 2)
      logger.info(s"someNumbers.contains(32)=${someNumbers.contains(32)}")
      logger.info(s"someNumbers.contains(33)=${someNumbers.contains(33)}")
      logger.info(s"someNumbers.contains(34)=${someNumbers.contains(34)}")
    }


    logger.info("------------------------> block line [4] <----------------------------")

    { // 范围可以指定包含其上限值：
      // 可以使用'to'指定包含范围
      val someNumbers = Range(0, 34, 2).inclusive
      logger.info(s"someNumbers.contains(32)=${someNumbers.contains(32)}")
      logger.info(s"someNumbers.contains(33)=${someNumbers.contains(33)}")
      logger.info(s"someNumbers.contains(34)=${someNumbers.contains(34)}")

      logger.info(s"(0 to 34 by 2) == someNumbers=${(0 to 34 by 2) == someNumbers}")
    }

    {
      // ? logger.info(s"${1 to 10 span (_ > 5)}")
      // ? logger.info(s"${1 to 10 splitAt 5}")
    }
  }
}
