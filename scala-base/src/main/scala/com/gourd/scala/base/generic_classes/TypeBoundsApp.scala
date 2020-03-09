package com.gourd.scala.base.generic_classes

import org.slf4j.LoggerFactory

/** 类型上界、下界
  * @author Li.Wei by 2019-08-07
  */
object TypeBoundsApp {
  val logger = LoggerFactory.getLogger("TypeBounds")

  def main(args: Array[String]): Unit = {}

  /**
    * 类型上界
    * 在Scala中，类型参数和抽象类型都可以有一个类型边界约束。
    * 这种类型边界在限制类型变量实际取值的同时还能展露类型成员的更多信息。
    * 比如像T <: A这样声明的类型上界表示类型变量T应该是类型A的子类。
    */
  {
    abstract class Animal {
      def name: String
    }
    abstract class Pet extends Animal {}

    class Cat extends Pet {
      override def name: String = "Cat"
    }

    class Dog extends Pet {
      override def name: String = "Dog"
    }

    class Lion extends Animal {
      override def name: String = "Lion"
    }
    // PetContainer接收的参数p必须是Pet的子类型P。
    class PetContainer[P <: Pet](p: P) {
      def pet: P = p
    }
    // Cat和Dog都是Pet的子类型，所以可以作为参数传入PetContainer中。
    val dogContainer = new PetContainer[Dog](new Dog)
    val catContainer = new PetContainer[Cat](new Cat)
    // Lion不是Pet的子类型，因此不可以传入PetContainer
    // 假如PetContainer[P <: Animal]这样定义，下面的初始化是可以的。
    val lionContainer = new PetContainer[Lion](new Lion) // 编译报错
  }

  /**
    * 类型下界
    * 类型下界是将类型声明为另一种类型的超类型。
    * B >: A 表示类型参数 B 或抽象类型 B 是类型 A 的超类型。
    */
  {
    // 这个程序不能编译，因为方法 prepend 中的参数 elem 是协变的 B 类型。
    // 这会出错，因为函数的参数类型是逆变的，而返回类型是协变的。
    trait Node[+B] {
      def prepend(elem: B): Node[B]
    }

    case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
      def prepend(elem: B): ListNode[B] = ListNode(elem, this)
      def head: B = h
      def tail: Node[B] = t
    }

    case class Nil[+B]() extends Node[B] {
      def prepend(elem: B): ListNode[B] = ListNode(elem, this)
    }

    // 我们需要将方法 prepend 的参数 elem 的型变翻转。
    // 我们通过引入一个新的类型参数 U 来实现这一点，该参数具有 B 作为类型下界
    trait Node[+B] {
      def prepend[U >: B](elem: U): Node[U]
    }

    case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
      def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
      def head: B = h
      def tail: Node[B] = t
    }

    case class Nil[+B]() extends Node[B] {
      def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
    }

    trait Bird
    case class AfricanSwallow() extends Bird
    case class EuropeanSwallow() extends Bird

    val africanSwallowList= ListNode[AfricanSwallow](AfricanSwallow(), Nil())
    // 可以为 Node[Bird] 赋值 africanSwallowList，然后再加入一个 EuropeanSwallow。
    val birdList: Node[Bird] = africanSwallowList
    birdList.prepend(EuropeanSwallow())
  }
}