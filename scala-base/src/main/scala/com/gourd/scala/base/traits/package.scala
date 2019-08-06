package com.gourd.scala.base

import org.slf4j.LoggerFactory

/**
  * 特质 (Traits) 用于在类 (Class)之间共享程序接口 (Interface)和字段 (Fields)。
  * 它们类似于Java 8的接口。 类和对象 (Objects)可以扩展特质，但是特质不能被实例化，因此特质没有参数。
  *
  * @author Li.Wei by 2019-08-06
  */
package object traits {
}

trait Iterator[A] {
  val a: Int = 1

  def hasNext: Boolean

  def next(): A
}

class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0

  override def hasNext: Boolean = current < to

  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}


object App {
  private val logger = LoggerFactory.getLogger(classOf[App])

  def main(args: Array[String]): Unit = {
    val iterator = new IntIterator(10)
    logger.info("{}", iterator.next()) // 0
    logger.info("{}", iterator.next()) // 1
  }
}