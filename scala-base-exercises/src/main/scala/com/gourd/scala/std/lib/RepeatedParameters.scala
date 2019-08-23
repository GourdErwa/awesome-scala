package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/**
  * 可变参数
  *
  * @author Li.Wei by 2019/8/22
  */
object RepeatedParameters {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { // 可变参数必须是最后一个参数
    def repeatedParameterMethod(x: Int, y: String, z: Any*) =
      "%d %ss can give you %s".format(x, y, z.mkString(", "))

    val result = repeatedParameterMethod(3, "egg", "a delicious sandwich", "protein", "high cholesterol")
    logger.info(s"result=$result")

    val result1 = repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol"))
    logger.info(s"result1=$result1")

    // List 扩展为可变参数
    val result2 = repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol"): _*)
    logger.info(s"result2=$result2")
  }

}
