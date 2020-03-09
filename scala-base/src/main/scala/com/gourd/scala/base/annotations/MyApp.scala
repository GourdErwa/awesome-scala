package com.gourd.scala.base.annotations

import org.slf4j.LoggerFactory

/** 注解
  * 注解将元信息与定义相关联
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * deprecated示例
    */
  {
    object DeprecationDemo {
      // 这个程序可以编译，但是编译器在编译的时候会打印出警告信息。
      @deprecated("deprecation message", "release # which deprecates method")
      def hello = "hola"

      hello
    }
  }

  /**
    * 确保编码正确性的注解
    * 如果不满足条件，某些注解实际上会导致编译失败。
    */
  {
    import scala.annotation.tailrec
    // 该方法由于添加了@tailrec注解，该注解的作用是要确保被注解的的方法是尾递归，否则会编译报错。
    // 尾递归是指递归调用是函数的最后一个语句，其结果被直接返回，不参与其他的计算。

    // 该方法在递归调用的时候，将返回的结果与x做了乘法计算，并不是直接返回结果，因此不是尾递归。
    /* def factorial(x: Int): Int = {
         @tailrec
         def factorialHelper(x: Int): Int = {
           if (x == 1) 1 else x * factorialHelper(x - 1)
         }

         factorialHelper(x)
       } */

    // 这样定义是尾递归，添加@tailred注解不会导致编译报错。
    def factorial2(x: Int): Int = {

      @tailrec
      def factorialHelper2(x: Int, accumulator: Int): Int = {
        if (x == 1) accumulator else factorialHelper2(x - 1, accumulator * x)
      }

      factorialHelper2(x, 1)
    }
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