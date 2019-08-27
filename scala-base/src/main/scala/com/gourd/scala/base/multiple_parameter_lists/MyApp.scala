package com.gourd.scala.base.multiple_parameter_lists

import org.slf4j.LoggerFactory

/**
  * 方法可以定义多个参数列表，当使用较少的参数列表调用多参数列表的方法时，会产生一个新的函数，该函数接收剩余的参数列表作为其参数。这被称为柯里化。
  *
  * [[Traversable]] : def foldLeft[B](z: B)(op: (B, A) => B): B
  * foldLeft从左到右，以此将一个二元运算op应用到初始值z和该迭代器（traversable)的所有元素上
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  {
    // 函数定义 int2int 等价于 int2intFunc1 等价于 int2intFunc2
    val int2int: Int => Int = a => a + 1
    val int2intFunc1: Function1[Int, Int] = new Function[Int, Int] {
      override def apply(v1: Int) = v1 + 1
    }
    val int2intFunc2: (Int) => Int = (v1: Int) => v1 + 1
  }

  {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val res = numbers.foldLeft(0)((m, n) => m * n)
    logger.info(s"$res") // 55

    // 单一的函数参数
    val r1 = numbers.foldLeft(0) { (m: Int, n: Int) => m * n }

    // 注意使用多参数列表时，我们还可以利用Scala的类型推断来让代码更加简洁（如下所示），而如果没有多参数列表，这是不可能的。
    // numbers.foldLeft(0)(_ + _)


    // 像上述语句这样，我们可以给定多参数列表的一部分参数列表（如上述的z）来形成一个新的函数（partially applied function），达到复用的目的
    // 如下所示：
    val numberFunc = numbers.foldLeft(List[Int]()) _
    val squares = numberFunc((xs, x) => xs :+ x * x)
    logger.info(squares.toString()) // List(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
    val cubes = numberFunc((xs, x) => xs :+ x * x * x)
    logger.info(cubes.toString()) // List(1, 8, 27, 64, 125, 216, 343, 512, 729, 1000)

    // 最后，foldLeft 和 foldRight 可以按以下任意一种形式使用
    // numbers.foldLeft(0)((sum, item) => sum * item) // Generic Form
    // numbers.foldRight(0)((sum, item) => sum * item) // Generic Form
    // numbers.foldLeft(0)(_ + _) // Curried Form
    // numbers.foldRight(0)(_ + _) // Curried Form
    // (0 /: numbers) (_ + _) // Used in place of foldLeft
    // (numbers :\ 0) (_ + _) // Used in place of foldRight
  }
}