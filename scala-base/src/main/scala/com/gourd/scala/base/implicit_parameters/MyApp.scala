package com.gourd.scala.base.implicit_parameters

import com.gourd.scala.base.implicit_parameters.MyApp.logger
import org.slf4j.LoggerFactory

/** 隐式参数
  * 方法可以具有 隐式 参数列表，由参数列表开头的 implicit 关键字标记。
  * 如果参数列表中的参数没有像往常一样传递， Scala 将查看它是否可以获得正确类型的隐式值，如果可以，则自动传递。
  *
  * Scala 将查找这些参数的位置分为两类：
  * 1.Scala 在调用包含有隐式参数块的方法时，将首先查找可以直接访问的隐式定义和隐式参数 (无前缀)。
  * 2.然后，它在所有伴生对象中查找与隐式候选类型相关的有隐式标记的成员。
  *
  * 更加详细的关于 Scala 隐式参数的指南请参考 https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 示例
    */
  {
    abstract class Monoid[A] {
      def add(x: A, y: A): A

      def unit: A
    }
    // 隐式参数
    implicit val stringMonoid: Monoid[String] = new Monoid[String] {
      def add(x: String, y: String): String = x concat y

      def unit: String = ""
    }
    // 隐式参数
    implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
      def add(x: Int, y: Int): Int = x + y

      def unit: Int = 0
    }

    // 隐式参数m，如果可以找到隐式Monoid[A]用于隐式参数m，我们在调用sum方法时只需要传入xs参数。
    def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
      if (xs.isEmpty) m.unit
      else m.add(xs.head, sum(xs.tail))

    // scala会在上下文寻找隐式值
    // 由于List(1,2,3)的类型为List[Int]，并且只传入了xs，因此会寻找Monoid[Int]的隐式参数
    // intMonoid是一个隐式定义，可以在main中直接访问。并且它的类型也正确，因此它会被自动传递给sum方法
    println(sum(List(1, 2, 3))) // 6
    // 由于List("a", "b", "c")的类型为List[String]，并且只传入了xs，因此会寻找Monoid[String]的隐式参数
    // stringMonoid是一个隐式定义，可以在main中直接访问。并且它的类型也正确，因此它会被自动传递给sum方法
    println(sum(List("a", "b", "c"))) // abc
  }
}