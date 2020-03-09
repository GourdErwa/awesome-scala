package com.gourd.scala.base.abstract_type_members

import org.slf4j.LoggerFactory

/** 抽象类型
  * 特质和抽象类可以包含一个抽象类型成员，意味着实际类型可由具体实现来确定。
  * 抽象类型可由 type 定义或者类的类型定义（泛型）
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 示例1
    */
  {
    trait Buffer {
      type T // 抽象类型，是用来描述成员element的类型的
      val element: T
    }

    abstract class SeqBuffer extends Buffer {
      type U // 另外一个抽象类型来限定上边界
      type T <: Seq[U] // 声明类型 T 只能是 Seq[U] 的子类，这个SeqBuffer就限定的元素只能是序列
      def length = element.length
    }

    abstract class IntSeqBuffer extends SeqBuffer {
      type U = Int
    }

    // 使用了IntSeqBuffer的匿名类实现方式
    def newIntSeqBuf(elem1: Int, elem2: Int): IntSeqBuffer =
      new IntSeqBuffer {
        type T = List[U] // 类型T被设置成了List[Int]
        val element = List(elem1, elem2)
      }

    val buf = newIntSeqBuf(7, 8)
    println("length = " + buf.length) // length = 2
    println("content = " + buf.element) // content = List(7, 8)
  }

  /**
    * 示例2
    * 把抽象类型成员转成类的类型参数或者反过来，也是可行的。
    * 本示例只用了类的类型参数来转换上面的示例，效果是相同的。
    * 有些情况下用类型参数替换抽象类型是行不通的。
    */
  {
    abstract class Buffer[+T] {
      val element: T
    }
    abstract class SeqBuffer[U, +T <: Seq[U]] extends Buffer[T] {
      def length = element.length
    }
    // 为了隐藏该方法返回的具体序列实现类型SeqBuffer[Int, List[Int]]，SeqBuffer的类型参数+T <: Seq[U]是必须的
    def newIntSeqBuf(e1: Int, e2: Int): SeqBuffer[Int, Seq[Int]] =
      new SeqBuffer[Int, List[Int]] {
        val element = List(e1, e2)
      }

    val buf = newIntSeqBuf(7, 8)
    println("length = " + buf.length)
    println("content = " + buf.element)
  }
}