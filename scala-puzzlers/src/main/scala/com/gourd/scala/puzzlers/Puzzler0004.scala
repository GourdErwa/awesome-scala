package com.gourd.scala.puzzlers

/**
  *
  * @author Li.Wei by 2019/10/30
  */
object Puzzler0004 extends App {

  trait A {
    val audience: String
    println("Hello " + audience)
  }

  class BMember(a: String = "World") extends A {
    override val audience = a
    println("I repeat: Hello " + audience)
  }

  class BConstructor(val audience: String = "World") extends A {
    println("I repeat: Hello " + audience)
  }

  new BMember("Readers")
  new BConstructor("Readers")

}
