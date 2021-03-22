package com.gourd.scala.exercises.fpinscala

import org.slf4j.LoggerFactory

import scala.annotation.tailrec

/**
  * https://www.scala-exercises.org/fp_in_scala/functional_data_structures
  *
  * @author Li.Wei by 2019/8/27
  */
object FunctionalDataStructures {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  sealed trait List[+A]

  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  case object Nil extends List[Nothing]

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    logger.info(s"List.apple(1, 2, 3).setHead(3)=${List(1, 2, 3).setHead(3)}")
    logger.info(s"""List.apple("a", "b").setHead("c")=${List("a", "b").setHead("c")}""")

    val intList = List(1, 2, 3)
    val nullList = List.apply()

    logger.info(s"intList.drop(1)=${intList.drop(1)}")
    logger.info(s"intList.drop(1)=${intList.drop(10)}")
    logger.info(s"nullList.drop(1)=${nullList.drop(10)}")

    logger.info(s"nullList.init=${intList.init}")
    logger.info(s"intList.foldRight(Nil: List[Int])(Cons(_, _))=${intList.foldRight(Nil: List[Int])(Cons(_, _))}")
    logger.info(s"intList.concat(List(List(4, 5, 6), List(7, 8)))=${intList.concat(List(List(4, 5, 6), List(7, 8)))}")
    logger.info(s"intList.flatMap(f => List(f + 1, f + 2))=${intList.flatMap(f => List(f + 1, f + 2))}")
    logger.info(s"intList.zipWith(List('a', 'b', 'c'))(_ + _)=${intList.zipWith(List('a', 'b', 'c'))(_ + _)}")


    logger.info(s"intList.startsWith(List(1,2))=${intList.startsWith(List(1, 2))}")
    logger.info(s"intList.startsWith(List(1,2,3,4))=${intList.startsWith(List(1, 2, 3, 4))}")

    logger.info(s"intList.hasSubsequence(List(1,2))=${intList.hasSubsequence(List(2, 3))}")
    logger.info(s"intList.hasSubsequence(List(1,2,3,4))=${intList.hasSubsequence(List(2, 3, 4))}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

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

  /** 移除头节点返回其余部分 */
  def tail: List[A]

  /** 移除尾节点返回其余部分 */
  def init: List[A] = this match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(h, t) => Cons(h, t.init)
  }

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

  // def reverse2: List[A] = this.foldLeft(List[A]())((acc, h) => Cons(h, acc))

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

  /** append */
  def append[U >: A](r: List[U]): List[U] = foldRight(r)(Cons(_, _))

  /** 将列表列表连接到一个列表中 */
  def concat[U >: A](l: List[List[U]]): List[U] = append(l.foldRight(Nil: List[U])(_.append(_)))

  /** 将列表添加到当前列表末尾 */
  def zipWith[B, C](o: List[B])(f: (A, B) => C): List[C] = (this, o) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), t1.zipWith(t2)(f))
  }

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

  def flatMap[B >: A](f: A => List[B]): List[B] = concat[B](map(f))

  def length: Int = foldLeft(0)((_, acc) => acc + 1)

  @annotation.tailrec
  final def startsWith[U >: A](prefix: List[U]): Boolean = (this, prefix) match {
    case (_, Nil) => true
    case (Cons(h, t), Cons(h2, t2)) if h == h2 => t.startsWith(t2)
    case _ => false
  }

  @annotation.tailrec
  final def hasSubsequence[U >: A](sub: List[U]): Boolean = this match {
    case Nil => sub == Nil
    case _ if this.startsWith(sub) => true
    case Cons(_, t) => t.hasSubsequence(sub)
  }
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

  def apply[A](as: A*): List[A] = if (as.isEmpty) Nil else Cons(as.head, apply(as.tail: _*))

}
