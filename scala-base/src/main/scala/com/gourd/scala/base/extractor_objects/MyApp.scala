package com.gourd.scala.base.extractor_objects

import scala.util.Random

/**
  * 提取器对象是一个包含有 unapply 方法的单例对象。apply 方法就像一个构造器，接受参数然后创建一个实例对象，
  * 反之 unapply 方法接受一个实例对象然后返回最初创建它所用的参数。提取器常用在模式匹配和偏函数中
  *
  * https://docs.scala-lang.org/tour/extractor-objects.html
  * @author Li.Wei by 2019-08-05
  */
object MyApp {
  def main(args: Array[String]): Unit = {}

  /**
    * 示例
    */
  {
    object CustomerID {

      def apply(name: String) = s"$name--${Random.nextLong}"

      def unapply(customerID: String): Option[String] = {
        val stringArray: Array[String] = customerID.split("--")
        if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
      }
    }
    // CustomerID("Sukyoung")是简化语法，默认调用了CustomerID.apply("Sukyoung")，创建了一个CustomerID字符串
    val customer1ID = CustomerID("Sukyoung") // Sukyoung--23098234908
    customer1ID match {
      // case CustomerID(name) => println(name) 默认是在调用提取器方法unapply
      case CustomerID(name) => println(name) // prints Sukyoung
      case _ => println("Could not extract a CustomerID")
    }

    val customer2ID = CustomerID("Nico")
    // 变量定义可以使用模式引入变量，提取器方法unapply可以用来初始化变量name
    // 等价于val name = CustomerID.unapply(customer2ID).get
    val CustomerID(name) = customer2ID
    println(name) // prints Nico
  }
}

