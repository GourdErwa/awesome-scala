package com.gourd.scala.puzzlers

/**
  * @author Li.Wei by 2019/10/30
  */
object Puzzler0001 extends App {

  List(1, 2).map { i => i + 1 }
  List(1, 2).map { i => println("Hi"); i + 1 }
  List(1, 2).map {
    _ + 1
  }
  List(1, 2).map {
    println("Hi");
    _ + 1
  }

}
