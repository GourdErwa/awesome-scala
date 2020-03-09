package com.gourd.scala.base.multiple_parameter_lists

import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

/**
  * 方法可以定义多个参数列表，当使用较少的参数列表调用多参数列表的方法时，会产生一个新的函数，该函数接收剩余的参数列表作为其参数。这被称为柯里化。
  *
  * [[Traversable]] : def foldLeft[B](z: B)(op: (B, A) => B): B
  * foldLeft从左到右，以此将一个二元运算op应用到初始值z和该迭代器（traversable)的所有元素上
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 示例
    */
  {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // 由于foldLeft是柯里化方法，有两个参数列表，当只使用第一个参数列表调用该方法时，
    // 会得到一个函数，该函数以剩余的参数列表作为参数，即(B, A) => B，
    // 由于第一个参数列表传入0，scala自动推断类型，得到(Int, Int) => Int，返回函数的类型为：((Int, Int) => Int) => Int
    val fold: ((Int, Int) => Int) => Int = numbers.foldLeft(0)
    val res = fold((x, y) => x + y)
    print(res) // 55

    // 上述调用可以实现方法的复用，可以在fold的基础上定义其他计算过程
    val res2 = fold((x, y) => x * y)

    // 如果不使用多参数列表，而是采用匿名函数作为参数调用方法，写法更为繁琐：
    numbers.foldLeft(0, { (m: Int, n: Int) => m + n })

    // 使用scala的类型推断可以让柯里化方法的调用更为简洁
    numbers.foldLeft(0)(_ + _)
  }

  /**
    * 有关foldLeft和foldRight的所有调用方法
    */
  {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    numbers.foldLeft(0)((sum, item) => sum + item)
    numbers.foldRight(0)((sum, item) => sum + item)

    numbers.foldLeft(0)(_ + _)
    numbers.foldRight(0)(_ + _)

    (0 /: numbers) (_ + _) // Used in place of foldLeft
    (numbers :\ 0) (_ + _) // Used in place of foldRight
  }

  /**
    * 隐式参数
    * 如果要指定参数列表中的某些参数为隐式(implicit)，应该使用多参数列表。
    */
  {
    def execute(arg: Int)(implicit ec: ExecutionContext) = ???
  }
}