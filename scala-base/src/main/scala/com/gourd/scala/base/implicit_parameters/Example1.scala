package com.gourd.scala.base.implicit_parameters

import com.gourd.scala.MainApp

/** 隐式参数
  * 方法可以具有 隐式 参数列表，由参数列表开头的 implicit 关键字标记。
  * 如果参数列表中的参数没有像往常一样传递， Scala 将查看它是否可以获得正确类型的隐式值，如果可以，则自动传递。
  *
  * Scala 将查找这些参数的位置分为两类：
  * 1.Scala 在调用包含有隐式参数块的方法时，将首先查找可以直接访问的隐式定义和隐式参数 (无前缀)。
  * 2.然后，它在所有伴生对象中查找与隐式候选类型相关的有隐式标记的成员。
  *
  * 更加详细的关于 Scala 隐式参数的指南请参考 https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html
  *
  * @author Li.Wei by 2019-08-06
  */
object Example1 extends MainApp

abstract class Monoid[A] {
  def add(x: A, y: A): A

  def unit: A
}

object ImplicitTest extends MainApp {
  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def add(x: String, y: String): String = x concat y

    def unit: String = ""
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y

    def unit: Int = 0
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))

  logger.info(s"${sum(List(1, 2, 3))}") // uses IntMonoid implicitly
  logger.info(s"${sum(List("a", "b", "c"))}") // uses StringMonoid implicitly
}