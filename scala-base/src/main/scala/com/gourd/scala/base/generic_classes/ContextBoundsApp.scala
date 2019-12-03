package com.gourd.scala.base.generic_classes

import com.gourd.scala.MainApp

import scala.reflect.ClassTag

/** 上下文界定 Context Bounds
  * Scala 2.8 引入的新特性
  *
  * [T : Ordering]
  * 说明存在一个隐式的值  Ordering[T](implicit ordered: Ordering[T])
  * 针对创建泛型数组的上下文界定：
  * [T : ClassTag]
  * [T : Manifest]
  *
  * @example
  * {{{
  * def foo[A : B](a: A) = g(a)
  * // 等价于
  * def foo[A](a : A)(implicit b : B[A]) = g(a)
  * }}}
  * @author Li.Wei by 2019-08-07
  */
object ContextBoundsApp extends MainApp {

  def mkArray[T: ClassTag](elems: T*): Array[T] = Array[T](elems: _*)

  val ints = mkArray(1, 2, 3)
  logger.info(s"$ints")
}

