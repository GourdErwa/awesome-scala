package com.gourd.scala.base.mixin_class_composition

/**
  * 通过混入（MIXIN）来组合类
  * 当某个特质被用于组合类时，被称为混入。
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  def main(args: Array[String]): Unit = {}

  /**
    * 类D有一个父类B和一个混入C，父类B和混入C具有相同的父类A
    * 一个类只能有一个父类，但是可以有多个混入，父类和混入可能具有相同的父类
    */
  {
    abstract class A {
      val message: String
    }
    class B extends A {
      val message = "I'm an instance of class B"
    }
    trait C extends A {
      def loudMessage = message.toUpperCase()
    }
    class D extends B with C

    val d = new D
    println(d.message) // I'm an instance of class B
    println(d.loudMessage) // I'M AN INSTANCE OF CLASS B
  }

  /**
    * 示例2
    * 抽象类，具有一个抽象类型T和标准的迭代器方法
    */
  {
    abstract class AbsIterator {
      type T

      def hasNext: Boolean

      def next(): T
    }
    // 实现类
    class StringIterator(s: String) extends AbsIterator {
      type T = Char
      private var i = 0

      def hasNext = i < s.length

      def next() = {
        val ch = s charAt i
        i += 1
        ch
      }
    }
    // 特质，继承了AbsIterator，定义了foreach方法，只要还有下一个元素，就会将下一个元素作为参数放入f函数中
    trait RichIterator extends AbsIterator {
      def foreach(f: T => Unit): Unit = while (hasNext) f(next())
    }
    // RichStringIter继承了StringIterator混入了RichIterator，使其功能更加灵活
    class RichStringIter extends StringIterator("Scala") with RichIterator

    val richStringIter = new RichStringIter
    richStringIter foreach println
    // S
    // c
    // a
    // l
    // a
  }
}
