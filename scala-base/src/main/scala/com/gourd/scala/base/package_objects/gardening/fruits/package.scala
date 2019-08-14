package com.gourd.scala.base.package_objects.gardening

/**
  * 一个包对象可能会混入多个特质
  * 注意，方法重载在包对象中不起作用
  *
  * @author Li.Wei by 2019-08-14
  */
package object fruits extends FruitAliases with FruitHelpers {
  val planted = List(Apple, Plum, Banana)

  def showFruit(fruit: Fruit): Unit = {
    println(s"${fruit.name}s are ${fruit.color}")
  }
}

trait FruitAliases

trait FruitHelpers
