package com.gourd.scala.base.tuples

import com.gourd.scala.MainApp

/**
  * 类型 Unit 的值 () 在概念上与类型 Tuple0 的值 () 相同。 Tuple0 只能有一个值，因为它没有元素。
  * 用户有时可能在元组和 case 类之间难以选择。 通常，如果元素具有更多含义，则首选 case 类
  *
  * @author Li.Wei by 2019-08-06
  */
object Example1 extends MainApp {

  // 定义 val ingredient = ("Sugar" , 25):Tuple2[String, Int]
  val ingredient = ("Sugar", 25): (String, Int)


  // 访问元素
  logger.info("{}", ingredient._1) // Sugar
  logger.info("{}", ingredient._2) // 25


  // 元组解构
  val (name, quantity) = ingredient
  logger.info("{}", name) // Sugar
  logger.info("{}", quantity) // 25


  // 元组解构-模式匹配
  val planetDistanceFromSun = List(("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6),
    ("Mars", 227.9), ("Jupiter", 778.3))
  planetDistanceFromSun.foreach { tuple => {
    tuple match {
      case ("Mercury", distance) => logger.info(s"Mercury is $distance millions km far from Sun")
      case p if (p._1 == "Venus") => logger.info(s"Venus is ${p._2} millions km far from Sun")
      case p if (p._1 == "Earth") => logger.info(s"Blue planet is ${p._2} millions km far from Sun")
      case _ => logger.info("Too far....")
    }
  }
  }

  // 元组解构-for
  val numPairs = List((2, 5), (3, -7), (20, 56))
  for ((a, b) <- numPairs) {
    logger.info(s"${a * b}")
  }
}
