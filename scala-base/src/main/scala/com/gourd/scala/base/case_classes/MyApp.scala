package com.gourd.scala.base.case_classes

import org.slf4j.LoggerFactory

/**
  * 案例类
  * 案例类（Case classes）和普通类差不多，只有几点关键差别，接下来的介绍将会涵盖这些差别。
  * 案例类非常适合用于不可变的数据
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 定义一个案例类
    * 一个最简单的案例类定义由关键字case class，类名，参数列表（可为空）组成。
    * 实例化案例类时不需要用关键字new，因为案例类有一个默认的apply方法来负责对象的创建。
    * 当你创建包含参数的案例类时，这些参数是公开（public）的val。
    * 在案例类中使用var也是可以的，但并不推荐这样。
    */
  {
    case class Book(isbn: String)
    val frankenstein = Book("978-0486282114")

    // Message的参数没有加关键字限定时，默认是public val的
    case class Message(sender: String, recipient: String, body: String)
    val message1 = Message("guillaume@quebec.ca", "jorge@catalonia.es", "Ça va ?")
    println(message1.sender) // prints guillaume@quebec.ca
    message1.sender = "travis@washington.us" // this line does not compile
  }

  /**
    * 比较
    * 案例类在比较的时候是按值比较而非按引用比较。
    */
  {
    // 尽管message2和message3引用不同的对象，但是他们的值是相等的，所以message2 == message3为true。
    case class Message(sender: String, recipient: String, body: String)

    val message2 = Message("jorge@catalonia.es", "guillaume@quebec.ca", "Com va?")
    val message3 = Message("jorge@catalonia.es", "guillaume@quebec.ca", "Com va?")
    println(message2 == message3) // true
  }

  /**
    * 拷贝
    * 你可以通过copy方法创建一个案例类实例的浅拷贝，同时可以指定构造参数来做一些改变。
    */
  {
    case class Message(sender: String, recipient: String, body: String)
    val message = Message("julien@bretagne.fr", "travis@washington.us", "Me zo o komz gant ma amezeg")
    // 指定message的recipient作为message1的sender，指定message1的recipient为”claire@bourgogne.fr”
    // 而message的body则是直接拷贝作为message1的body了。
    val message1 = message.copy(sender = message.recipient, recipient = "claire@bourgogne.fr")
    println(message1.sender) // travis@washington.us
    println(message1.recipient) // claire@bourgogne.fr
    println(message1.body) // "Me zo o komz gant ma amezeg"
  }
}

