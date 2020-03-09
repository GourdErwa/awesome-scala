package com.gourd.scala.base.traits

import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

/**
  * 特质 (Traits) 用于在类 (Class)之间共享程序接口 (Interface)和字段 (Fields)。
  * 它们类似于Java 8的接口。 类和对象 (Objects)可以扩展特质，但是特质不能被实例化，因此特质没有参数。
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 使用特质
    * 使用extends关键字来实现特质
    * 使用override关键字来实现特质里面的任何抽象成员
    */
  {
    trait Iterator[A] {
      def hasNext: Boolean

      def next(): A
    }

    // IntIterator将参数to作为hasNext方法比较的上限，并扩展了Iterator的类型为Int，因此next方法必须返回Int类型的值
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

    val iterator = new IntIterator(10)
    iterator.next() // returns 0
    iterator.next() // returns 1
  }

  /**
    * 子类型
    * 凡是需要特质的地方，都可以由该特质的子类型来替换。
    */
  {
    trait Pet {
      val name: String
    }

    class Cat(val name: String) extends Pet
    class Dog(val name: String) extends Pet

    val dog = new Dog("Harry")
    val cat = new Cat("Sally")
    // 此处集合中需要放入Pet类型的对象，因为Cat和Dog实现了Pet特质，因此对象dog和cat可以放入该集合
    val animals = ArrayBuffer.empty[Pet]
    animals.append(dog)
    animals.append(cat)
    animals.foreach(pet => println(pet.name)) // Prints Harry Sally
  }
}