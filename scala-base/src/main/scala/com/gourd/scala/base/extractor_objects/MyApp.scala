package com.gourd.scala.base.extractor_objects

import scala.util.Random

/**
  * 提取器对象是一个包含有 unapply 方法的单例对象。apply 方法就像一个构造器，接受参数然后创建一个实例对象，
  * 反之 unapply 方法接受一个实例对象然后返回最初创建它所用的参数。提取器常用在模式匹配和偏函数中
  *
  * https://docs.scala-lang.org/tour/extractor-objects.html
  *
  * @author Li.Wei by 2019-08-05
  */
object MyApp {

  def apply(name: String) = s"$name--${Random.nextLong}"

  def unapply(customerID: String): Option[String] = {
    val stringArray: Array[String] = customerID.split("--")
    if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
  }

  def main(args: Array[String]): Unit = {
    val customer1ID = MyApp("SukYoung") // SukYoung--23098234908
    customer1ID match {
      case MyApp(name) => println(name) // prints SukYoung
      case _ => println("Could not extract a CustomerID")
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    val customer2ID = MyApp("Nico")
    val MyApp(name) = customer2ID
    println(name)  // prints Nico
    //上面的代码等价于 val name = MyApp.unapply(customer2ID).get

  }
}

