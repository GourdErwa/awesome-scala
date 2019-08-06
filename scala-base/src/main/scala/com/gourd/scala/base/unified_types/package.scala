package com.gourd.scala.base

/**
  * Any是所有类型的超类型，也称为顶级类型。
  * 它定义了一些通用的方法如equals、hashCode和toString。Any有两个直接子类：AnyVal和AnyRef。
  *
  * AnyVal代表值类型。
  * 有9个预定义的非空的值类型分别是：Double、Float、Long、Int、Short、Byte、Char、Unit和Boolean。
  * Unit是不带任何意义的值类型，它仅有一个实例可以像这样声明：()。
  * 所有的函数必须有返回，所以说有时候Unit也是有用的返回类型。
  *
  * AnyRef代表引用类型。
  * 所有非值类型都被定义为引用类型。在Scala中，每个用户自定义的类型都是AnyRef的子类型。
  * 如果Scala被应用在Java的运行环境中，AnyRef相当于java.lang.Object。
  *
  * Nothing是所有类型的子类型，也称为底部类型。没有一个值是Nothing类型的。
  * 它的用途之一是给出非正常终止的信号，如抛出异常、程序退出或者一个无限循环
  * （可以理解为它是一个不对值进行定义的表达式的类型，或者是一个不能正常返回的方法）。
  *
  * Null是所有引用类型的子类型（即AnyRef的任意子类型）。
  * 它有一个单例值由关键字null所定义。Null主要是使得Scala满足和其他JVM语言的互操作性，但是几乎不应该在Scala代码中使用。
  * 我们将在后面的章节中介绍null的替代方案。
  *
  * @author Li.Wei by 2019-08-06
  */
package object unified_types {
  val list: List[Any] = List(
    "a string",
    732, // an integer
    'c', // a character
    true, // a boolean value
    () => "an anonymous function returning a string"
  )

  list.foreach(element => println(element))
  //  a string
  //    732
  //  c
  //  true
  //  <function>

}
