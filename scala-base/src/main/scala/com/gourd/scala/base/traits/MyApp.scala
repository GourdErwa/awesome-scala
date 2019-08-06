package com.gourd.scala.base.traits

import org.slf4j.LoggerFactory

/**
  * 特质 (Traits) 用于在类 (Class)之间共享程序接口 (Interface)和字段 (Fields)。
  * 它们类似于Java 8的接口。 类和对象 (Objects)可以扩展特质，但是特质不能被实例化，因此特质没有参数。
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {
    val iterator = new IntIterator(10)
    logger.info("{}", iterator.next()) // 0
    logger.info("{}", iterator.next()) // 1
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


  // 特质（trait）和类（class）可以用sealed标记为密封的，这意味着其所有子类都必须与之定义在相同文件中，从而保证所有子类型都是已知的
  sealed abstract class Furniture

  case class Couch() extends Furniture

  case class Chair() extends Furniture

}