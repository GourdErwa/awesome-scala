package com.gourd.scala.overviews.collections.custom.list

import org.slf4j.LoggerFactory

import scala.annotation.tailrec

/** 自定义实现 scala List
  *
  * @author Li.Wei by 2019-08-19
  */
sealed trait List[+A] {

  def size: Int = this match {
    case Nil => 0
    case Cons(_, tail) => 1 + tail.size
  }

  def isEmpty: Boolean = size == 0

  def head: A

  def tail: List[A]

  def headOpt[U >: A](h: U): List[U] = this match {
    case Nil => Cons(h, Nil)
    case Cons(_, next) => Cons(h, next)
  }

  def tailOpt[U >: A](t: List[U]): List[U] = this match {
    case Nil => Nil
    case Cons(h, _) => Cons(h, t)
  }

  def reverse: List[A] = {
    var result: List[A] = Nil
    var these = this
    while (!these.isEmpty) {
      result = Cons(these.head, result)
      these = these.tail
    }
    result
  }

  def drop(n: Int): List[A] = {
    @tailrec
    def loop(n: Int, lag: List[A]): List[A] = if (n <= 0 || lag.isEmpty) lag else loop(n - 1, lag.tail)

    loop(n, this)
  }

  def dropWhile(f: A => Boolean): List[A] = {
    var these = this
    while (!these.isEmpty && f(these.head)) {
      these = these.tail
    }
    these
  }

  @tailrec
  final def dropWhile2(f: A => Boolean): List[A] = this match {
    case _ => this
    case Cons(h, t) if f(h) => t.dropWhile2(f)
  }

  /** 非尾递归实现 */
  final def foldRight[B](z: B)(f: (A, B) => B): B = this match {
    case Nil => z
    case Cons(h, next) => f(h, next.foldRight(z)(f))
  }

  /** 尾递归实现 */
  @tailrec
  final def foldLeft[B](z: B)(f: (A, B) => B): B = this match {
    case Nil => z
    case Cons(h, t) => t.foldLeft(f(z, h))(f)
  }

  def length: Int = foldRight(0)((_, acc) => acc + 1)
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
  def apple[A](as: A*): List[A] = if (as.isEmpty) Nil else Cons(as.head, apple(as.tail: _*))

}

object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {

    val ex1 = Cons(1, Nil)
    logger.info(s"ex1=$ex1")

    val listInt = List.apple(1, 2, 3, 4, 5)
    logger.info(s"listInt=$listInt")
    logger.info(s"listInt.size=${listInt.size}")
    logger.info(s"listInt.length=${listInt.length}")

    logger.info(s"caseVal=${
      listInt match {
        case Nil => 42
        case Cons(x, Cons(2, Cons(4, _))) => x
        case Cons(h, t) => h + t.size
        // case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
        // case Cons(h, t) => h + List.sum(t)
        case _ => 101
      }
    }")
    logger.info(s"listInt.head=${listInt.head}")
    logger.info(s"listInt.tail=${listInt.tail}")
    logger.info(s"listInt.headOpt(11)=${listInt.headOpt(11)}")
    logger.info(s"listInt.tailOpt(Cons(11, Cons(22, Nil)))=${listInt.tailOpt(Cons(11, Cons(22, Nil)))}")

    logger.info(s"listInt.reverse=${listInt.reverse}")

    logger.info(s"listInt.drop(2)=${listInt.drop(2)}")
    logger.info(s"listInt.drop(listInt.size + 10)=${listInt.drop(listInt.size + 10)}")

    logger.info(s"listInt.dropWhile(_ == 2)=${listInt.dropWhile(_ == 2)}")
    logger.info(s"listInt.dropWhile(_ % 2 == 1)=${listInt.dropWhile(_ % 2 == 1)}")

    logger.info(s"listInt.dropWhile2(_ == 2)=${listInt.dropWhile2(_ == 2)}")
    logger.info(s"listInt.dropWhile2(_ % 2 == 1)=${listInt.dropWhile2(_ % 2 == 1)}")

    logger.info(s"listInt.foldRight(0)(_ + _)=${listInt.foldRight(0)(_ + _)}") // 等价于 1+2+3+4+5

    logger.info(s"listInt.foldRight(Nil:List[Int])(Cons(_, _))=${listInt.foldRight(Nil: List[Int])(Cons(_, _))}")

  }
}