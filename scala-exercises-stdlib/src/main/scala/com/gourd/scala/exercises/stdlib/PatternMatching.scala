package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** 模式匹配
  *
  * @author Li.Wei by 2019-08-21
  */
object PatternMatching {
  private val logger = LoggerFactory.getLogger("PatternMatching")


  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    val stuff = "blue"

    val myStuff = stuff match {
      case "red" => println("RED"); 1
      case "blue" => println("BLUE"); 2
      case "green" => println("GREEN"); 3
      case _ => println(stuff); 0 // case _ will trigger if all other cases fail.
    }

    logger.info(s"myStuff=$myStuff")

  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    def goldilocks(expr: Any) = expr match {
      case ("porridge", "Papa") => "Papa eating porridge"
      case ("porridge", "Mama") => "Mama eating porridge"
      case ("porridge", "Baby") => "Baby eating porridge"
      case _ => "what?"
    }

    logger.info(s"""goldilocks(("porridge", "Mama"))=${goldilocks(("porridge", "Mama"))}""")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  {
    // 通配符匹配
    def goldilocks(expr: Any) = expr match {
      case ("porridge", _) => "eating"
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby") => "sleeping"
      case _ => "what?"
    }

    logger.info(s"""goldilocks(("porridge", "Papa"))=${goldilocks(("porridge", "Papa"))}""")
    logger.info(s"""goldilocks(("chair", "Mama"))=${goldilocks(("chair", "Mama"))}""")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { // 引用匹配值
    def goldilocks(expr: Any) = expr match {
      case ("porridge", bear: String) => bear.concat(" said someone's been eating my porridge")
      case ("chair", bear: String) => bear.concat(" said someone's been sitting in my chair")
      case ("bed", bear: String) => bear.concat(" said someone's been sleeping in my bed")
      case _ => "what?"
    }

    logger.info(s"""goldilocks(("porridge", "Papa"))=${goldilocks(("porridge", "Papa"))}""")
    logger.info(s"""goldilocks(("chair", "Mama"))=${goldilocks(("chair", "Mama"))}""")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  {
    // 反引号引用变量
    val foodItem = "porridge"

    def goldilocks(expr: Any) = expr match {
      case (`foodItem`, _) => "eating"
      case ("chair", "Mama") => "sitting"
      case ("bed", "Baby") => "sleeping"
      case _ => "what?"
    }

    logger.info(s"""goldilocks(("porridge", "Papa"))=${goldilocks(("porridge", "Cousin"))}""")
    logger.info(s"""goldilocks(("chair", "Mama"))=${goldilocks(("chair", "Mama"))}""")
    logger.info(s"""goldilocks(("porridge", "Cousin"))=${goldilocks(("porridge", "Cousin"))}""")
    logger.info(s"""goldilocks(("beer", "Cousin"))=${goldilocks(("beer", "Cousin"))}""")

    def patternEquals(i: Int, j: Int) = j match {
      case `i` => true
      case _ => false
    }

    logger.info(s"""patternEquals(3, 3)=${patternEquals(3, 3)}""")
    logger.info(s"""patternEquals(7, 9)=${patternEquals(7, 9)}""")
    logger.info(s"""patternEquals(9, 9)=${patternEquals(9, 9)}""")
  }

  logger.info("------------------------> block line [6] <----------------------------")

  { // 将列表拆分为多个部分，在本例中为头部x和尾部xs
    val secondElement = List(1, 2, 3) match {
      case x :: xs => xs.head // 拆分为 List(1), List(2, 3)
      case _ => 0
    }
    logger.info(s"""secondElement=$secondElement""")

    logger.info(
      s"""扩展示例1=${
        List(1, 2, 3) match {
          case ::(head, next) => next
          case Nil => Nil
        }
      }""")

    logger.info(
      s"""扩展示例2=${
        List(1, 2, 3) match {
          case x :: xs => xs
          case Nil => Nil
        }
      }""")

    logger.info(
      s"""扩展示例3=${
        List(1, 2, 3) match {
          case x :: y :: xs => y
          case Nil => Nil
        }
      }""")

    logger.info(
      s"""扩展示例4=${
        List(1, 2) match {
          case x :: y :: xs => xs
          case Nil => 0
        }
      }""")

    logger.info(
      s"""扩展示例5=${
        List(1, 2, 3) match {
          case x :: y :: Nil => y // only matches a list with exactly two items
          case _ => 0
        }
      }""")

    logger.info(
      s"""扩展示例6=${
        List(1, 2, 3) match {
          case x :: y :: z :: tail => tail
          case _ => 0
        }
      }""")
  }

}
