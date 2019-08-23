package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/**
  * 字符串格式化
  *
  * https://www.scala-exercises.org/std_lib/Formatting
  *
  * @author Li.Wei by 2019-08-21
  */
object Formatting {
  private val logger = LoggerFactory.getLogger("Formatting")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    val s = "Hello World"

    logger.info("Application %s".format(s))

    val a = 'a'
    val b = 'B'
    //format(a)是字符串格式，“%c”.format(x) 将返回字符的字符串表示形式。
    logger.info("%c".format(a))
    logger.info("%c".format(b))

  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    logger.info(s""""%c".format('a')=${"%c".format('a')}""")
    // logger.info(s""""%c".format('\141')=${"%c".format('\141')}""")
    logger.info(s""""%c".format('\"')=${"%c".format('\"')}""")
    logger.info(s""""%c".format('\\')=${"%c".format('\\')}""")

    //"%d bottles of beer on the wall" format 190 - 100
  }
}
