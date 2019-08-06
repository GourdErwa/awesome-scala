package com.gourd.scala.base.basics

/**
  * @author Li.Wei by 2019-08-06
  */
object Basics {

  def main(args: Array[String]): Unit = {

  }

  def expressions(): Unit = {
    println(1) // 1
    println(1 + 1) // 2
    println("Hello!") // Hello!
    println("Hello," + " world!") // Hello, world!

    val x = 1 + 1
    println(x) // 2
    // x = 3 // This does not compile.
    val y: Int = 1 + 1

    (x: Int) => x + 1
  }

  def function(): Unit = {
    val addOne = (x: Int) => x + 1
    println(addOne(1)) // 2

    val add = (x: Int, y: Int) => x + y
    println(add(1, 2)) // 3

    val getTheAnswer = () => 42
    println(getTheAnswer()) // 42
  }

  def methods(): Unit = {
    def add(x: Int, y: Int): Int = x + y

    println(add(1, 2)) // 3

    def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier

    println(addThenMultiply(1, 2)(3)) // 9

    def name: String = System.getProperty("user.name")

    println("Hello, " + name + "!")

    def getSquareString(input: Double): String = {
      val square = input * input
      square.toString
    }
  }


}
