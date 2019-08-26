package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** 名称参数
  * 将函数作为参数进行传递
  *
  * https://www.scala-exercises.org/std_lib/byname_parameter
  *
  * @author Li.Wei by 2019/8/22
  */
object ByNameParameter {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { // () => Int是一种采用Unit类型的函数类型 ，可以将其作为方法参数放置
    def calc(x: () => Int): Either[Throwable, Int] = {
      try {
        Right(x()) //An explicit call of the x function
      } catch {
        case b: Throwable => Left(b)
      }
    }

    //Having explicitly declaring that Unit is a parameter with ()
    val y = calc { () => 14 + 15 }
    logger.info(s"y=$y")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { //
    def calc(x: => Int): Either[Throwable, Int] = { //x is a call by name parameter
      try {
        Right(x)
      } catch {
        case b: Throwable => Left(b)
      }
    }

    val y = calc {
      //This looks like a natural block
      println("Here we go!") //Some superfluous call
      val z = List(1, 2, 3, 4) //Another superfluous call
      49 + 20
    }
    logger.info(s"y=$y")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // 通过名称参数也可以用于object和apply进行有趣的块状调用：
    object Pig {
      def apply(x: => String) = x.tail + x.head + "ay"
    }

    val result: String = Pig {
      val x = "pret"
      val z = "zel"
      x ++ z //concatenate the strings
    }
    logger.info(s"result=$result")
  }

}
