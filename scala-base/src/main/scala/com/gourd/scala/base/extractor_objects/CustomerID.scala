package com.gourd.scala.base.extractor_objects

import scala.util.Random

/**
  * https://docs.scala-lang.org/tour/extractor-objects.html
  *
  * @author Li.Wei by 2019-08-05
  */
object CustomerID {

  def apply(name: String) = s"$name--${Random.nextLong}"

  def unapply(customerID: String): Option[String] = {
    val stringArray: Array[String] = customerID.split("--")
    if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
  }

  def main(args: Array[String]): Unit = {
    val customer1ID = CustomerID("SukYoung") // SukYoung--23098234908
    customer1ID match {
      case CustomerID(name) => println(name) // prints SukYoung
      case _ => println("Could not extract a CustomerID")
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    val customer2ID = CustomerID("Nico")
    val CustomerID(name) = customer2ID
    println(name)  // prints Nico
    //上面的代码等价于 val name = CustomerID.unapply(customer2ID).get

  }
}

