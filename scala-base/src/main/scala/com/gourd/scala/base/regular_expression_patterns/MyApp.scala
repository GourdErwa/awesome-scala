package com.gourd.scala.base.regular_expression_patterns

import org.slf4j.LoggerFactory

import scala.util.matching.Regex

/**
  * 正则表达式是用来找出数据中的指定模式（或缺少该模式）的字符串。
  * .r方法可使任意字符串变成一个正则表达式。
  * 还可以使用括号来同时匹配多组正则表达式。
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 示例
    */
  {
    // numberPattern的类型是正则表达式类Regex，其作用是确保密码中包含一个数字。
    val numberPattern: Regex = "[0-9]".r
    numberPattern.findFirstMatchIn("awesomepassword") match {
      case Some(_) => logger.info("Password OK")
      case None => logger.info("Password must contain a number")
    }
  }

  logger.info("> ------------------------------------------")

  // 多组正则表达式。
  {
    val keyValPattern: Regex = "([0-9a-zA-Z-#() ]+): ([0-9a-zA-Z-#() ]+)".r

    val input: String =
      """background-color: #A03300;
        |background-image: url(img/header100.png);
        |background-position: top center;
        |background-repeat: repeat-x;
        |background-size: 2160px 108px;
        |margin: 0;
        |height: 108px;
        |width: 100%;""".stripMargin

    for (patternMatch <- keyValPattern.findAllMatchIn(input))
      logger.info(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")

    // 上例解析出了一个字符串中的多个键和值，其中的每个匹配又有一组子匹配，结果如下：

    //    key: background-color value: #A03300
    //    key: background-image value: url(img
    //    key: background-position value: top center
    //    key: background-repeat value: repeat-x
    //    key: background-size value: 2160px 108px
    //    key: margin value: 0
    //    key: height value: 108px
    //    key: width value: 100
  }
}