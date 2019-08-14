package com.gourd.scala.base.package_objects

import com.gourd.scala.base.package_objects.gardening.fruits
import org.slf4j.LoggerFactory

/** 包对象
  * Scala 提供包对象作为在整个包中方便的共享使用的容器。
  * 包对象中可以定义任何内容，而不仅仅是变量和方法。 例如，包对象经常用于保存包级作用域的类型别名和隐式转换。 包对象甚至可以继承 Scala 的类和特质。
  *
  * 按照惯例，包对象的代码通常放在名为 package.scala 的源文件中。
  *
  * 每个包都允许有一个包对象。 在包对象中的任何定义都被认为是包自身的成员。
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}
}

// in file PrintPlanted.scala

import com.gourd.scala.base.package_objects.gardening.fruits._

object PrintPlanted {
  def main(args: Array[String]): Unit = {
    for (fruit <- fruits.planted) {
      showFruit(fruit)
    }
  }
}