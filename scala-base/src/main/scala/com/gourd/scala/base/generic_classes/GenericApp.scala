package com.gourd.scala.base.generic_classes

import com.gourd.scala.MainApp

/** 泛型类
  * 指可以接受类型参数的类
  *
  * @author Li.Wei by 2019-08-06
  */
class Stack[A] {
  private var elements: List[A] = scala.collection.immutable.Nil

  def push(x: A): Unit = elements = x :: elements

  def pop(): A = {
    val currentTop = peek
    elements = elements.tail
    currentTop
  }

  def peek: A = elements.head
}

class Fruit

class Apple extends Fruit

class Banana extends Fruit


object GenericApp extends MainApp {

  {
    // int 型
    val stackInt = new Stack[Int]
    stackInt.push(1)
    stackInt.push(2)
    logger.info(s"${stackInt.pop()}") // prints 2
    logger.info(s"${stackInt.pop()}") // prints 1
  }
  {
    // Fruit 型
    val stackFruit = new Stack[Fruit]
    val apple = new Apple
    val banana = new Banana
    stackFruit.push(apple)
    stackFruit.push(banana)
  }
}
