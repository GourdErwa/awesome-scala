package com.gourd.scala.base.generic_classes

import com.gourd.scala.MainApp

/** 类型上界、下界
  * `[T <: Comparable[T]]`
  * <: ：指明上界，表达了泛型的类型必须是"某种类型"或某种类型的"子类"
  * >: ：指明下界，表达了泛型的类型必须是"某种类型"或某种类型的"父类"
  *
  * @author Li.Wei by 2019-08-07
  */
///////////////////////////////////////////////////////////////////////////////////
// 类型上界
// T <: A这样声明的类型上界表示类型变量T应该是类型A的子类
///////////////////////////////////////////////////////////////////////////////////
abstract class TypeBoundsAnimal {
  def name: String
}

abstract class TypeBoundsPet extends TypeBoundsAnimal {}

class TypeBoundsCat extends TypeBoundsPet {
  override def name: String = "Cat"
}

class TypeBoundsDog extends TypeBoundsPet {
  override def name: String = "Dog"
}

class TypeBoundsLion extends TypeBoundsAnimal {
  override def name: String = "Lion"
}

class PetContainer[P <: TypeBoundsPet](p: P) {
  def pet: P = p
}

// val dogContainer = new PetContainer[Dog](new Dog)
// val catContainer = new PetContainer[Cat](new Cat)

// val lionContainer = new PetContainer[Lion](new Lion) // 不可编译 Lion不是Pet的子类


///////////////////////////////////////////////////////////////////////////////////
// 类型下界
// B >: A 表示类型参数 B 或抽象类型 B 是类型 A 的超类型。
// 在大多数情况下，A 将是类的类型参数，而 B 将是方法的类型参数
///////////////////////////////////////////////////////////////////////////////////

/*
这个程序 不能 编译，因为方法 prepend 中的参数 elem 是协变的 B 类型。
这会出错，因为函数的参数类型是逆变的，而返回类型是协变的。

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
*/

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


object TypeBoundsApp extends MainApp {


  val africanSwallowList: ListNode[AfricanSwallow] = ListNode[AfricanSwallow](AfricanSwallow(), Nil())
  val birdList: Node[Bird] = africanSwallowList // 协变
  birdList.prepend(new EuropeanSwallow)
}