package com.gourd.scala.base.mixin_class_composition

import com.gourd.scala.MainApp

/**
  * @author Li.Wei by 2019-08-06
  */
object Example2 extends MainApp

abstract class AbsIterator {
  type T

  def hasNext: Boolean

  def next(): T
}

// StringIterator带有一个String类型参数的构造器，可用于对字符串进行迭代。
class StringIterator(s: String) extends AbsIterator {
  type T = Char
  private var i = 0

  def hasNext: Boolean = i < s.length

  def next(): Char = {
    val ch = s charAt i
    i += 1
    ch
  }
}

trait RichIterator extends AbsIterator {
  def foreach(f: T => Unit): Unit = while (hasNext) f(next())
}

object StringIteratorTest extends MainApp {

  val richStringIter = new RichStringIter

  // 我们要把StringIterator和RichIterator 中的功能组合成一个类。
  class RichStringIter extends StringIterator("Scala") with RichIterator

  richStringIter foreach println
}