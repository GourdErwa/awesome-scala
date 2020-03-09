package com.gourd.scala.base.generic_classes

import org.slf4j.LoggerFactory

/** 泛型类
  * 指可以接受类型参数的类
  *
  * @author Li.Wei by 2019-08-06
  */
object GenericApp {
  private val logger = LoggerFactory.getLogger("GenericApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 定义泛型类
    * 泛型类使用方括号 [] 来接受类型参数。
    * 一个惯例是使用字母 A 作为参数标识符，当然你可以使用任何参数名称。
    */
  object Generic{
    class Stack[A] {
      private var elements: List[A] = scala.collection.immutable.Nil // 只可以存放A类型的元素
      def push(x: A) { elements = x :: elements } // 将A类型元素添加到elements头部，生成新的集合
      def peek: A = elements.head
      def pop(): A = {
        val currentTop = peek
        elements = elements.tail
        currentTop
      }
    }
  }

  /**
    * 使用
    * 要使用一个泛型类，将一个具体类型放到方括号中来代替 A。
    * 如果类型参数有子类型，子类型可以被传入。
    */
  {
    import com.gourd.scala.base.generic_classes.GenericApp.Generic.Stack
    // stact可以接受Int类型数据
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    println(stack.pop)  // prints 2
    println(stack.pop)  // prints 1

    class Fruit
    class Apple extends Fruit
    class Banana extends Fruit
    // stack1可以接受Fruit类型数据，Fruit子类型数据同样也可以接受
    val stack1 = new Stack[Fruit]
    val apple = new Apple
    val banana = new Banana

    stack1.push(apple)
    stack1.push(banana)
  }
}
