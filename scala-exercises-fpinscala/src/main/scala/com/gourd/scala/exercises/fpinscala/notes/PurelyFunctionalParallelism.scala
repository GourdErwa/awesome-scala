package com.gourd.scala.exercises.fpinscala.notes

/**
  * @author Li.Wei by 2019/8/27
  */
object PurelyFunctionalParallelism {

  /*def sum1(value: IndexedSeq[Int]): Int =
    if (value.size <= 1)
      value.headOption getOrElse 0
    else {
      val (l, r) = value.splitAt(value.length / 2)
      sum1(l) + sum1(r)
    }*/

  /*def sum2(value: IndexedSeq[Int]): Int =
    if (value.size <= 1)
      value.headOption getOrElse 0
    else {
      val (l, r) = value.splitAt(value.length / 2)
      val sumL: Par[Int] = Par.unit(sum2(l))
      val sumR: Par[Int] = Par.unit(sum2(r))
      Par.get(sumL) + Par.get(sumR)
    }*/

  /*def sum3(value: IndexedSeq[Int]): Par[Int] =
    if (value.size <= 1)
      Par.unit(value.headOption getOrElse 0)
    else {
      val (l, r) = value.splitAt(value.length / 2)
      Par.map2(sum3(l), sum3(r))(_ + _)
    }*/

  /*def sum4(value: IndexedSeq[Int]): Par[Int] =
    if (value.length <= 1)
      Par.unit(value.headOption getOrElse 0)
    else {
      val (l,r) = value.splitAt(value.length/2)
      Par.map2(Par.fork(sum4(l)), Par.fork(sum4(r)))(_ + _)
    }*/
}

/*

trait Par[T] {
  def get[A](a: Par[A]): A

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = Par.unit(f(get(a), get(b)))

}

object Par {

  /** 将一个恒定值变为一个并行计算 */
  def unit[A](a: => A): Par[A] = null

  /** 代表要并行计算，实际上不会求值，直到run被调用 */
  def fork[A](a: => A): Par[A] = null

  /** 包装一个标记为并发的未求值计算 */
  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  /** 从实际执行的计算中获取值 */
  def run[A](a: Par[A]): A = _

}*/
