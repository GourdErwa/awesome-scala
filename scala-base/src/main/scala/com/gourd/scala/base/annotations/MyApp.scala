package com.gourd.scala.base.annotations

import org.slf4j.LoggerFactory

/** 注解
  * 注解将元信息与定义相关联
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}


  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  // deprecated
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  { // 这个程序可以编译，但编译器将打印一个警告信息: “there was one deprecation warning”。
    object DeprecationDemo extends App {
      @deprecated("deprecation message", "release # which deprecates method")
      def hello = "hola"

      hello
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  // 确保编码正确性的注解
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  /* @tailrec
    如果不满足条件，某些注解实际上会导致编译失败。 例如，注解 @tailrec 确保方法是 尾递归。 尾递归可以保持内存需求不变

    尾递归
    尾递归是指递归调用是函数的最后一个语句，而且其结果被直接返回，这是一类特殊的递归调用。
    由于递归结果总是直接返回，尾递归比较方便转换为循环，因此编译器容易对它进行优化。现在很多编译器都对尾递归有优化，程序员们不必再手动将它们改写为循环。
   */
  {
    import scala.annotation.tailrec

    def factorial(x: Int): Int = {

      @tailrec
      def factorialHelper(x: Int, accumulator: Int): Int = {
        if (x == 1) accumulator else factorialHelper(x - 1, accumulator * x)
      }

      factorialHelper(x, 1)
    }

    factorial(2)

    /*def factorial(x: Int): Int = {
      @tailrec
      def factorialHelper(x: Int): Int = {
        if (x == 1) 1 else x * factorialHelper(x - 1) // 我们将得到一个错误信息 “Recursive call not in tail position”.
      }
      factorialHelper(x)
    }*/

  }


  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  // 影响代码生成的注解
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  /* @inline
    像 @inline 这样的注解会影响生成的代码(即你的 jar 文件可能与你没有使用注解时有不同的字节)。
    内联表示在调用点插入被调用方法体中的代码。 生成的字节码更长，但有希望能运行得更快。
    使用注解 @inline 并不能确保方法内联，当且仅当满足某些生成代码大小的启发式算法时，它才会触发编译器执行此操作。
   */
}