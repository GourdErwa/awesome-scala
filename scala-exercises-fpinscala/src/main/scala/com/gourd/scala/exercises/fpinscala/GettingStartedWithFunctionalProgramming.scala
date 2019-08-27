package com.gourd.scala.exercises.fpinscala

import org.slf4j.LoggerFactory

/**
  * @author Li.Wei by 2019/8/27
  */
object GettingStartedWithFunctionalProgramming {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  {
    // 函数定义 int2int 等价于 int2intFunc1 等价于 int2intFunc2
    val int2int: Int => Int = a => a + 1
    val int2intFunc1: Function1[Int, Int] = new Function[Int, Int] {
      override def apply(v1: Int) = v1 + 1
    }
    val int2intFunc2: (Int) => Int = (v1: Int) => v1 + 1
  }

  logger.info("------------------------> block line [1] <----------------------------")

  { // 练习2.1：
    // 斐波那契数列递归
    def fib(n: Int): Int = {
      @annotation.tailrec def loop(n: Int, prev: Int, cur: Int): Int =
        if (n <= 0) prev else loop(n - 1, cur, prev + cur)

      loop(n, 0, 1)
    }

    logger.info(s"fib(5)=${fib(5)}") // 5 = 3+4
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { // 练习2.2：
    def isSorted[A](as: Array[A], ordering: (A, A) => Boolean): Boolean = {
      @annotation.tailrec def go(n: Int): Boolean =
        if (n >= as.length - 1) true
        else if (ordering(as(n), as(n + 1))) false
        else go(n + 1)

      go(0)
    }

    val r1 = isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x > y)
    val r2 = isSorted(Array(7, 5, 1, 3), (x: Int, y: Int) => x < y)
    val r3 = isSorted(Array("Scala", "Exercises"), (x: String, y: String) => x.length > y.length)
    logger.info(s"r1=$r1")
    logger.info(s"r2=$r2")
    logger.info(s"r3=$r3")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // 练习2.3：
    // Currying 是一种转换，它将f两个参数的函数转换为部分适用的一个参数的函数f
    def curry[A, B, C](f: (A, B) => C): A => B => C =
      a => b => f(a, b)

    def f(a: Int, b: Int): Int = a + b

    def g(a: Int)(b: Int): Int = a + b

    logger.info(s"curry(f)(1)(1) == f(1, 1)=${curry(f)(1)(1) == f(1, 1)}")
    logger.info(s"curry(f)(1)(1) == g(1)(1)=${curry(f)(1)(1) == g(1)(1)}")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { // 练习2.4：
    // Currying 逆向转换
    def uncurry[A, B, C](f: A => B => C): (A, B) => C =
      (a, b) => f(a)(b)

    def f(a: Int, b: Int): Int = a + b

    def g(a: Int)(b: Int): Int = a + b

    logger.info(s"uncurry(g)(1, 1) == g(1)(1)=${uncurry(g)(1, 1) == g(1)(1)}")
    logger.info(s"uncurry(g)(1, 1) == f(1, 1)=${uncurry(g)(1, 1) == f(1, 1)}")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { // 练习2.5：
    // 函数组合将一个函数的输出提供给另一个函数的输入
    def compose[A, B, C](f: B => C, g: A => B): A => C =
      a => f(g(a))

    def f(b: Int): Int = b / 2

    def g(a: Int): Int = a + 2

    logger.info(s"compose(f, g)(0) == compose(g, f)(0)=${compose(f, g)(0) == compose(g, f)(0)}")
    logger.info(s"compose(f, g)(2)=${compose(f, g)(2)}")
    logger.info(s"compose(g, f)(2)=${compose(g, f)(2)}")
  }
}
