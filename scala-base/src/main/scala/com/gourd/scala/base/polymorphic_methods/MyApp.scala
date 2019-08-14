package com.gourd.scala.base.polymorphic_methods

import org.slf4j.LoggerFactory

/** 多态方法
  * Scala 中的方法可以按类型和值进行参数化。 语法和泛型类类似。 类型参数括在方括号中，而值参数括在圆括号中。
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  /*
  方法 listOfDuplicates 具有类型参数 A 和值参数 x 和 length。 值 x 是 A 类型。
  如果 length < 1，我们返回一个空列表。 否则我们将 x 添加到递归调用返回的重复列表中。
 （注意，:: 表示将左侧的元素添加到右侧的列表中。）
   */
  def listOfDuplicates[A](x: A, length: Int): List[A] = {
    if (length < 1)
      Nil
    else
      x :: listOfDuplicates(x, length - 1)
  }

  def main(args: Array[String]): Unit = {
    logger.info(listOfDuplicates[Int](3, 4).toString()) // List(3, 3, 3, 3)
    logger.info(listOfDuplicates("La", 8).toString()) // List(La, La, La, La, La, La, La, La)
  }
}