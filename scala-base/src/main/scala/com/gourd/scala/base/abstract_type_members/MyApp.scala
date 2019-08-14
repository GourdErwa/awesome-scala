package com.gourd.scala.base.abstract_type_members

import org.slf4j.LoggerFactory

/** 抽象类型
  * 特质和抽象类可以包含一个抽象类型成员，意味着实际类型可由具体实现来确定。
  * 抽象类型可由 type 定义或者类的类型定义（泛型）
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 抽象类型成员:使用 type 定义抽象类型
///////////////////////////////////////////////////////////////////////////////////////////////////////
trait Buffer {
  type T
  val element: T
}

// 这里定义的抽象类型T是用来描述成员element的类型的。通过抽象类来扩展这个特质后，就可以添加一个类型上边界来让抽象类型T变得更加具体。
abstract class SeqBuffer extends Buffer {
  type U
  type T <: Seq[U]

  def length = element.length
}

abstract class IntSeqBuffer extends SeqBuffer {
  type U = Int
}

object Test {
  def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer =
    new IntSeqBuffer {
      type T = List[U]
      val element = List(elem1, elem2)
    }

  def main(args: Array[String]): Unit = {
    val buf = newIntSeqBuf(7, 8)
    println("length = " + buf.length)
    println("content = " + buf.element)
  }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
//类的类型参数:使用 泛型 定义抽象类型
///////////////////////////////////////////////////////////////////////////////////////////////////////
abstract class Buffer1[+T] { // 该处需协变，以支持匿名实现类初始化
  val element: T
}

abstract class SeqBuffer1[U, +T <: Seq[U]] extends Buffer1[T] {
  def length = element.length
}

class IntSeqBuf1 extends SeqBuffer1[Int, List[Int]] {
  override val element = List(1, 2)
}

object Test1 {

  // 需要注意的是为了隐藏从方法newIntSeqBuf返回的对象的具体序列实现的类型，这里的型变标号（+T <: Seq[U]）是必不可少的。
  // 此外要说明的是，有些情况下用类型参数替换抽象类型是行不通的。
  def newIntSeqBuf1(e1: Int, e2: Int): SeqBuffer1[Int, Seq[Int]] =
    new SeqBuffer1[Int, List[Int]] {
      override val element = List(e1, e2)
    }

  def main(args: Array[String]): Unit = {
    val buf: SeqBuffer1[Int, Seq[Int]] = newIntSeqBuf1(7, 8)
    println("length = " + buf.length)
    println("content = " + buf.element)
  }
}