package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/** 部分应用函数
  * https://www.scala-exercises.org/std_lib/partially_applied_functions
  *
  * @author Li.Wei by 2019/8/22
  */
object PartiallyAppliedFunctions {
  private val logger = LoggerFactory.getLogger("PartiallyAppliedFunctions")

  def main(args: Array[String]): Unit = {

    logger.info("------------------------> block line [1] <----------------------------")

    { // 部分应用的函数是您不应用任何或所有参数的函数，从而创建另一个函数。此部分应用的函数不应用任何参数。
      def sum(a: Int, b: Int, c: Int) = a + b + c

      val sum3 = sum _

      logger.info(s"sum3(1, 9, 7)=${sum3(1, 9, 7)}")
      logger.info(s"sum(4, 5, 6)=${sum(4, 5, 6)}")
    }

    logger.info("------------------------> block line [2] <----------------------------")

    {
      def sum(a: Int, b: Int, c: Int) = a + b + c

      val sumC = sum(1, 10, _: Int)
      logger.info(s"sumC(4)=${sumC(4)}")
      logger.info(s"sum(4, 5, 6)=${sum(4, 5, 6)}")
    }

    logger.info("------------------------> block line [3] <----------------------------")

    { // Currying是一种将具有多个参数的函数转换为多个函数的技术，每个函数都有一个参数 Function2
      def multiply(x: Int, y: Int) = x * y
      // Function2
      logger.info(s"(multiply _).isInstanceOf[Function2[_, _, _]]=${(multiply _).isInstanceOf[(_, _) => _]}")

      val multiplyCurried: Int => Int => Int = (multiply _).curried
      logger.info(s"multiply(4, 5)=${multiply(4, 5)}")
      logger.info(s"multiplyCurried(3)(2)=${multiplyCurried(3)(2)}")

      val multiplyCurriedFour: Int => Int = multiplyCurried(4)
      logger.info(s"multiplyCurriedFour(2)=${multiplyCurriedFour(2)}")
      logger.info(s"multiplyCurriedFour(4)=${multiplyCurriedFour(4)}")
    }

    logger.info("------------------------> block line [4] <----------------------------")

    { // Currying是一种将具有多个参数的函数转换为多个函数的技术，每个函数都有一个参数 Function3
      def multiply(x: Int, y: Int, z: Int) = x * y * z
      // Function3
      logger.info(s"(multiply _).isInstanceOf[Function2[_, _, _]]=${(multiply _).isInstanceOf[(_, _, _) => _]}")

      val multiplyCurried = (multiply _).curried
      logger.info(s"multiply(4, 5)=${multiply(4, 5, 6)}")
      logger.info(s"multiplyCurried(3)(2)=${multiplyCurried(3)(2)}")
      logger.info(s"multiplyCurried(3)(2)(10)=${multiplyCurried(3)(2)(10)}")
    }

    logger.info("------------------------> block line [5] <----------------------------")

    { // Currying允许您创建通用函数的专用版本：
      def customFilter(f: Int => Boolean)(xs: List[Int]) = xs filter f

      def onlyEven(x: Int) = x % 2 == 0

      val xs = List(12, 11, 5, 20, 3, 13, 2)

      logger.info(s"customFilter(onlyEven)(xs)=${customFilter(onlyEven)(xs)}")

      val onlyEvenFilter = customFilter(onlyEven) _
      logger.info(s"onlyEvenFilter(xs) =${onlyEvenFilter(xs)}")
    }

  }
}
