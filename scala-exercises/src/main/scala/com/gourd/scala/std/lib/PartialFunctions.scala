package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/** 偏函数
  * [[PartialFunction]]
  * https://www.scala-exercises.org/std_lib/partial_functions
  *
  * 对比 Function 和 Partial Function，更学术味的解释如下：
  * Function          对给定的输入参数类型，函数可接受该类型的任何值。换句话说，一个(Int) => String 的函数可以接收任意Int值，并返回一个字符串。
  * Partial Function  对给定的输入参数类型，偏函数只能接受该类型的某些特定的值。一个定义为(Int) => String 的偏函数可能不能接受所有Int值为输入。
  *
  * 常用方法说明
  * andThen:            对结果进一步处理，isEven.andThen(println)(2)
  * applyOrElse:        isEven.applyOrElse(1,isOdd)
  * compose:            和 andThen类似，但是先调用最后一个，再调用第一个。isEven.compose((x:Int) => x+2)(2) //4
  * lift:               转换成一个正常的函数，isEven.lift(2)，返回Option
  * orElse:             组合，扩大参数的值域
  * runWith:            isEven.runWith(println)(2). 等价if(pf isDefinedAt x) { action(pf(x)); true } else false.返回结果类型为(A) => Boolean，这和andThen不同，andThen返回PartialFunction[A, C]
  *
  * @author Li.Wei by 2019/8/22
  */
object PartialFunctions {
  private val logger = LoggerFactory.getLogger("PartialFunctions")

  def main(args: Array[String]): Unit = {

    logger.info("------------------------> block line [1] <----------------------------")

    { //
      val doubleEvens: PartialFunction[Int, Int] =
        new PartialFunction[Int, Int] {
          override def isDefinedAt(x: Int) = x % 2 == 0 // 声明匹配条件

          override def apply(v1: Int) = v1 * 2 // 声明匹配条件后处理逻辑
        }

      val tripleOdds: PartialFunction[Int, Int] = new PartialFunction[Int, Int] {
        override def isDefinedAt(x: Int) = x % 2 != 0

        override def apply(v1: Int) = v1 * 3
      }

      val whatToDo: PartialFunction[Int, Int] = doubleEvens orElse tripleOdds // 偏函数组合
      logger.info(s"whatToDo(3)=${whatToDo(3)}")
      logger.info(s"whatToDo(4)=${whatToDo(4)}")
    }

    logger.info("------------------------> block line [2] <----------------------------")

    { // 创建case语句时，会自动创建apply和isDefinedAt方法
      val doubleEvens: PartialFunction[Int, Int] = {
        case x if (x % 2) == 0 => x * 2
      }
      val tripleOdds: PartialFunction[Int, Int] = {
        case x if (x % 2) != 0 => x * 3
      }
      val whatToDo = doubleEvens orElse tripleOdds // 偏函数组合
      logger.info(s"whatToDo(3)=${whatToDo(3)}")
      logger.info(s"whatToDo(4)=${whatToDo(4)}")
    }

    logger.info("------------------------> block line [3] <----------------------------")

    { // 部分函数的结果可以andThen在链的末尾添加一个函数：
      val doubleEvens: PartialFunction[Int, Int] = {
        case x if (x % 2) == 0 => x * 2
      }
      val tripleOdds: PartialFunction[Int, Int] = {
        case x if (x % 2) != 0 => x * 3
      }

      val addFive = (x: Int) => x + 5
      val whatToDo = doubleEvens orElse tripleOdds andThen addFive
      logger.info(s"whatToDo(3)=${whatToDo(3)}")
      logger.info(s"whatToDo(4)=${whatToDo(4)}")
    }

    logger.info("------------------------> block line [4] <----------------------------")

    { // andThen 可以用来继续到另一个逻辑链：
      val doubleEvens: PartialFunction[Int, Int] = {
        case x if (x % 2) == 0 => x * 2
      }
      val tripleOdds: PartialFunction[Int, Int] = {
        case x if (x % 2) != 0 => x * 3
      }

      val printEven: PartialFunction[Int, String] = {
        case x if (x % 2) == 0 => "Even"
      }
      val printOdd: PartialFunction[Int, String] = {
        case x if (x % 2) != 0 => "Odd"
      }

      val whatToDo = doubleEvens orElse tripleOdds andThen (printEven orElse printOdd)
      logger.info(s"whatToDo(3)=${whatToDo(3)}")
      logger.info(s"whatToDo(4)=${whatToDo(4)}")
    }

  }
}
