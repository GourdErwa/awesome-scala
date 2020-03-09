package com.gourd.scala.base.polymorphic_methods

import org.slf4j.LoggerFactory

/** 多态方法
  * Scala 中的方法可以按类型和值进行参数化。 语法和泛型类类似。 类型参数括在方括号中，而值参数括在圆括号中。
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 示例
    * 方法 listOfDuplicates 具有类型参数 A 和值参数 x 和 length。 值 x 是 A 类型。
    * 如果 length < 1，我们返回一个空列表。 否则我们将 x 添加到递归调用返回的重复列表中。
    */
  {
    def listOfDuplicates[A](x: A, length: Int): List[A] = {
      if (length < 1)
        Nil
      else
        x :: listOfDuplicates(x, length - 1) // :: 表示将左侧的元素添加到右侧的列表中。
    }
    // 第一次调用，显示的指定了类型参数为Int，因此第一个值参数必须为Int类型。
    println(listOfDuplicates[Int](3, 4)) // List(3, 3, 3, 3)
    // 第二次调用没有显示的提供参数类型，编译器可以根据上下文进行推断。
    // 由于传入的第一个参数是String类型的，因此编译器知道参数类型 A 必须为String。
    println(listOfDuplicates("La", 8)) // List(La, La, La, La, La, La, La, La)
  }
}