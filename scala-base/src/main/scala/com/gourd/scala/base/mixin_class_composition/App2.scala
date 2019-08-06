package com.gourd.scala.base.mixin_class_composition

import org.slf4j.LoggerFactory

/**
  * 类型 Unit 的值 () 在概念上与类型 Tuple0 的值 () 相同。 Tuple0 只能有一个值，因为它没有元素。
  * 用户有时可能在元组和 case 类之间难以选择。 通常，如果元素具有更多含义，则首选 case 类
  *
  * @author Li.Wei by 2019-08-06
  */
object App2 {
  private val logger = LoggerFactory.getLogger("App2")

  def main(args: Array[String]): Unit = {
  }
}

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

object StringIteratorTest extends App {

  // 我们要把StringIterator和RichIterator 中的功能组合成一个类。
  class RichStringIter extends StringIterator("Scala") with RichIterator

  val richStringIter = new RichStringIter
  richStringIter foreach println
}