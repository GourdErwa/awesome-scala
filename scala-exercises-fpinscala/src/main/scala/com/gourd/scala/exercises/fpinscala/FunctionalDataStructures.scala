package com.gourd.scala.exercises.fpinscala

import org.slf4j.LoggerFactory

import scala.annotation.tailrec

/**
  * @author Li.Wei by 2019/8/27
  */
object FunctionalDataStructures {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  sealed trait List[+A]

  case object Nil extends List[Nothing]

  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    logger.info(s"List.apple(1, 2, 3).setHead(3)=${List(1, 2, 3).setHead(3)}")
    logger.info(s"""List.apple("a", "b").setHead("c")=${List("a", "b").setHead("c")}""")

    val intList = List(1, 2, 3)
    val nullList = List.apply()
    println(intList.drop(1))
    println(nullList.drop(10))
    println(nullList.drop(10))
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { //
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { //
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { //
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { //
  }
}

sealed trait List[+A] {

  /** 链表长度计算 结果与[[List.length]] 一致 */
  def size: Int = this match {
    case Nil => 0
    case Cons(_, tail) => 1 + tail.size
  }

  def isEmpty: Boolean = size == 0

  /** 链表头节点 */
  def head: A

  /** 移除头结点返回其余部分 */
  def tail: List[A]

  /** 设值链表头节点，替换已存在的头节点
    *
    * setHead(List(1, 2, 3), 3) shouldBe List(3, 2, 3)
    * setHead(List("a", "b"), "c") shouldBe List("c", "b")
    */
  def setHead[U >: A](h: U): List[U] = this match {
    case Nil => Cons(h, Nil)
    case Cons(_, next) => Cons(h, next)
  }

  /** 设值链表头节点 */
  def setTail[U >: A](t: List[U]): List[U] = this match {
    case Nil => Nil
    case Cons(h, _) => Cons(h, t)
  }

  /** 反转 */
  def reverse: List[A] = {
    var result: List[A] = Nil
    var these = this
    while (!these.isEmpty) {
      result = Cons(these.head, result)
      these = these.tail
    }
    result
  }

  /** 从头删除指定个数节点后返回剩余链表 */
  def drop(n: Int): List[A] = {
    @tailrec
    def loop(n: Int, lag: List[A]): List[A] = if (n <= 0 || lag.isEmpty) lag else loop(n - 1, lag.tail)

    loop(n, this)
  }

  /** 从头循环删除节点，有函数不为true退出删除操作返回剩余链表 */
  def dropWhile(f: A => Boolean): List[A] = {
    var these = this
    while (!these.isEmpty && f(these.head)) {
      these = these.tail
    }
    these
  }

  /** 从头循环删除节点，有函数不为true退出删除操作返回剩余链表 尾递归实现 */
  @tailrec
  final def dropWhile2(f: A => Boolean): List[A] = this match {
    case _ => this
    case Cons(h, t) if f(h) => t.dropWhile2(f)
  }

  /** 非尾递归实现 不推荐 请参考 foldLeft */
  final def foldRight[B](z: B)(f: (A, B) => B): B = this match {
    case Nil => z
    case Cons(h, next) => f(h, next.foldRight(z)(f))
  }

  /** 尾递归实现 */
  @tailrec
  final def foldLeft[B](z: B)(f: (A, B) => B): B = this match {
    case Nil => z
    case Cons(h, t) => t.foldLeft(f(h, z))(f)
  }

  def zipWith[U >: A](o: List[U]): List[A] = ???

  /*def append(aa: List[A]): List[A] = aa match {
    case Nil => this
    case Cons(h, next) => Cons(h, append(next))
  }*/

  def map[B](f: A => B): List[B] = {
    var result: List[B] = Nil
    var these = this
    while (!these.isEmpty) {
      result = Cons(f(these.head), result)
      these = these.tail
    }
    result.reverse
  }

  def map1[B](f: A => B): List[B] = foldRight(Nil: List[B])((h, t) => Cons(f(h), t))

  // def flatMap[B](f: A => List[B]): List[B] = foldRight(Nil: List[B])((h, t) => Cons(f(h), t))

  def length: Int = foldLeft(0)((_, acc) => acc + 1)
}

case object Nil extends List[Nothing] {
  override def head: Nothing = throw new UnsupportedOperationException("head of empty list")

  override def tail: Nothing = throw new UnsupportedOperationException("tail of empty list")
}

case class Cons[+A](h: A, next: List[A]) extends List[A] {
  override def head = h

  override def tail = next

}

object List {

  /** head 返回列表第一个元素,tail 返回一个列表，包含除了第一元素之外的其他元素 */
  def apply[A](as: A*): List[A] = if (as.isEmpty) Nil else Cons(as.head, apply(as.tail: _*))

}
