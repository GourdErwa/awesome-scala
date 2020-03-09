package com.gourd.scala.base.implicit_conversions

import org.slf4j.LoggerFactory

/** 隐式转换
  * 一个从类型 S 到类型 T 的隐式转换由一个函数类型 S => T 的隐式值来定义，或者由一个可转换成所需值的隐式方法来定义。
  *
  * 隐式转换在两种情况下会用到：
  * 1.如果一个表达式 e 的类型为 S， 并且类型 S 不符合表达式的期望类型 T。
  * 2.在一个类型为 S 的实例对象 e 中调用 e.m， 如果被调用的 m 并没有在类型 S 中声明。
  * 在第一种情况下，搜索转换 c，它适用于 e，并且结果类型为 T。
  * 在第二种情况下，搜索转换 c，它适用于 e，其结果包含名为 m 的成员。
  *
  * 更加详细的关于 Scala 隐式参数的指南请参考 https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html
  * https://scala-lang.org/files/archive/spec/2.13/07-implicits.html
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 情况1示例
    */
  {
    implicit def int2Integer(x: String): Integer =
      Integer.valueOf(x)

    def add(a: Integer, b: Integer) = a + b
    // "1"和"23"的类型都为String，并不符合add方法需要的Integer类型。
    // 由于定义了隐式转换，从上文找到了合适的隐式转换，String被转成了Integer， String => Integer
    println(add("1", "23"))
  }

  /**
    * 情况2示例
    */
  {
    import scala.language.implicitConversions
    implicit def list2ordered[A](x: List[A]): Ordered[List[A]] =
      new Ordered[List[A]] {
        def compare(that: List[A]): Int = 1
      }

    // List[Int]本身是没有compare方法的，但是上面定义了隐式转换，List[A] => Ordered[List[A]]。
    // 因此，List[Int]类型的数据可以直接调用compare方法。
    List(1, 2, 3).compare(List(3, 2, 1))
  }
}
