package com.gourd.scala.base.tuples

import org.slf4j.LoggerFactory

/**
  * 在 Scala中，元组是一个可以容纳不同类型元素的类。 元组是不可变的，当我们需要从函数返回多个值时，元组会派上用场。
  * 用户有时可能在元组和case类之间难以选择。通常，如果元素具有更多含义，则首选 case 类。
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 元组的定义与访问
    * Scala中的元组包含一系列类：Tuple2，Tuple3等，直到Tuple22。
    * 当我们创建一个包含n个元素(n位于2和22 之间)的元组时，Scala基本上就是从上述的一组类中实例化一个相对应的类，使用组成元素的类型进行参数化。
    * 使用下划线语法来访问元组中的元素。'tuple._n'取出了第n个元素(假设有足够多元素)。
    */
  {
    // 定义元组
    val ingredient = ("Sugar", 25): Tuple2[String, Int]
    // 访问元素
    println(ingredient._1) // Sugar
    println(ingredient._2) // 25
  }

  /**
    * 解构元组数据
    * 在()中定义数量相同的、不同名称的变量用来接收元组中的数据。
    * 元组解构可以用于模式匹配或者for表达式中。
    */
  {
    // 元组解构
    val ingredient = ("Sugar", 25): Tuple2[String, Int]
    val (name, quantity) = ingredient
    println(name) // Sugar
    println(quantity) // 25

    // 用于模式匹配
    val planetDistanceFromSun = List(("Mercury", 57.9), ("Venus", 108.2),
      ("Earth", 149.6), ("Mars", 227.9), ("Jupiter", 778.3))
    planetDistanceFromSun.foreach { tuple => {
      tuple match {
        case ("Mercury", distance) => println(s"Mercury is $distance millions km far from Sun")
        case p if (p._1 == "Venus") => println(s"Venus is ${p._2} millions km far from Sun")
        case p if (p._1 == "Earth") => println(s"Blue planet is ${p._2} millions km far from Sun")
        case _ => println("Too far....")
      }
    }
    }

    // 用于for表达式中
    val numPairs = List((2, 5), (3, -7), (20, 56))
    for ((a, b) <- numPairs) {
      println(a * b)
    }
  }
}
