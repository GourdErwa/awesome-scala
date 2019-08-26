package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

import scala.language.postfixOps

/** 命名和默认参数
  *
  * @author Li.Wei by 2019/8/23
  */
object NamedAndDefaultArguments {
  private val logger = LoggerFactory.getLogger("NamedAndDefaultArguments")

  def main(args: Array[String]): Unit = {}

  class WithoutClassParameters() {
    def addColors(red: Int, green: Int, blue: Int) = (red, green, blue)

    def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) = (red, green, blue)
  }

  class WithClassParameters(val defaultRed: Int, val defaultGreen: Int, val defaultBlue: Int) {
    def addColors(red: Int, green: Int, blue: Int) =
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)

    def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) =
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)
  }

  class WithClassParametersInClassDefinition(val defaultRed: Int = 0, val defaultGreen: Int = 255, val defaultBlue: Int = 100) {
    def addColors(red: Int, green: Int, blue: Int) =
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)

    def addColorsWithDefaults(red: Int = 0, green: Int = 0, blue: Int = 0) =
      (red + defaultRed, green + defaultGreen, blue + defaultBlue)
  }


  logger.info("------------------------> block line [1] <----------------------------")

  {
    val me = new WithoutClassParameters()
    // 如果使用它们的名称，可以按任何顺序指定参数：
    val myColor: (Int, Int, Int) = me.addColors(green = 0, red = 255, blue = 0)
    logger.info(s"myColor=$myColor")
    // 仅指定部分参数
    val myColor1: (Int, Int, Int) = me.addColorsWithDefaults(red = 1)
    logger.info(s"myColor1=$myColor1")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { // 如果将它们保留为关闭，则可以访问类参数和默认参数：
    val me = new WithClassParameters(40, 50, 60)
    val myColor = me.addColors(green = 50, red = 60, blue = 40)
    logger.info(s"myColor=$myColor")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // 可以使用默认类参数并使用默认参数：
    val me = new WithClassParameters(10, 20, 30)
    val myColor = me.addColorsWithDefaults(green = 70)
    logger.info(s"myColor=$myColor")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  {
    val me = new WithClassParametersInClassDefinition()
    val myColor = me.addColorsWithDefaults(green = 70)
    logger.info(s"myColor=$myColor")
  }
}
