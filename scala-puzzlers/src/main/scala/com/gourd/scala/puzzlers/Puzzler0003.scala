package com.gourd.scala.puzzlers

/**
  * https://stackoverflow.com/questions/2727612/scalas-tuple-unwrapping-nuance
  *
  * @author Li.Wei by 2019/10/30
  */
object Puzzler0003 extends App {

  val IJ: (Int, Int) = (3, 4)
  // var (I, J): (Int, Int) = (3, 4)
  val (i, j): (Int, Int) = (3, 4)

  println(IJ)
  println(i + j)

  /*
  前者是类型正确的元组的赋值。后者试图与两个常量I和J匹配（3，4）– Scala 假定模式匹配中的大写变量是常量。
   */
}

// 赋值操作
object Puzzler0003Ext1 extends App {
  val iter = Iterator(1, 2, 3)
  val A, B, C = iter.next()
  println(A) // 1
  println(B) // 2
  println(C) // 3
}