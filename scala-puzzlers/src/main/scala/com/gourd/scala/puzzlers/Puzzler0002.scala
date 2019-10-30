package com.gourd.scala.puzzlers

/**
  * https://stackoverflow.com/questions/2727612/scalas-tuple-unwrapping-nuance
  *
  * @author Li.Wei by 2019/10/30
  */
object Puzzler0002 extends App {

  // 编译器异常
  // val ij: (Int, Int) = ("3", "4")

  // 模式匹配，运行时异常
  // val (i, j): (Int, Int) = ("3", "4")

}
