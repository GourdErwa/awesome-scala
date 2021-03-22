package com.gourd.scala.base.case_classes

import com.gourd.scala.MainApp

/**
  * 案例类
  * 案例类（Case classes）和普通类差不多，只有几点关键差别，接下来的介绍将会涵盖这些差别。
  * 案例类非常适合用于不可变的数据
  *
  * @author Li.Wei by 2019-08-06
  */
object Example1 extends MainApp {

  val message0 = Message("guillaume@quebec.ca", "jorge@catalonia.es") // 实例化
  val message1 = Message("guillaume@quebec.ca", "jorge@catalonia.es", "Ça va ?") // 实例化
  // 比较
  // 案例类在比较的时候是按值比较而非按引用比较：
  val message2 = Message("jorge@catalonia.es", "guillaume@quebec.ca", "Com va?")

  println(message1.sender) // prints guillaume@quebec.ca
  // 不能给message1.sender重新赋值，因为它是一个val（不可变）。在案例类中使用var也是可以的，但并不推荐这样
  // message1.sender = "travis@washington.us" // this line does not compile
  val message3 = Message("jorge@catalonia.es", "guillaume@quebec.ca", "Com va?")
  val messagesAreTheSame = message2 == message3 // true
  // 拷贝
  // 你可以通过copy方法创建一个案例类实例的浅拷贝，同时可以指定构造参数来做一些改变。
  val message4 = Message("julien@bretagne.fr", "travis@washington.us", "Me zo o komz gant ma amezeg")
  val message5 = message4.copy(sender = message4.recipient, recipient = "claire@bourgogne.fr")

  // 定义一个案例类 ， 默认为 val 不可变类型
  case class Message(val sender: String, var recipient: String, body: String = "default val")

  logger.info(message5.sender) // travis@washington.us
  logger.info(message5.recipient) // claire@bourgogne.fr
  logger.info(message5.body) // "Me zo o komz gant ma amezeg"

}

