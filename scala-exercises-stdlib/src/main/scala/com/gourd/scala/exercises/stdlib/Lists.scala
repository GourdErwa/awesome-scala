package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** [[List]]
  * https://www.scala-exercises.org/std_lib/lists
  *
  * @author Li.Wei by 2019-08-21
  */
object Lists {
  private val logger = LoggerFactory.getLogger("Lists")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    // eq tests identity (same object):
    // == tests equality (same content):
    val a = List(1, 2, 3)
    val b = List(1, 2, 3)
    logger.info(s"a eq b=${a eq b}")
    logger.info(s"a == b=${a == b}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    // Nil列表是相同的，即使是不同类型：
    // case object Nil extends List[Nothing] ...
    val a: List[String] = Nil
    val b: List[Int] = Nil

    logger.info(s"a eq Nil=${a eq Nil}")
    logger.info(s"a == Nil=${a == Nil}")

    logger.info(s"b eq Nil=${b eq Nil}")
    logger.info(s"b == Nil=${b == Nil}")

    logger.info(s"a eq b=${a eq b}")
    logger.info(s"a == b=${a == b}")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  {
    /**
      * override def headOption: Option[A] =
      * if (isEmpty) None else Some(head)
      *
      * def tail: C = {
      * if (isEmpty) throw new UnsupportedOperationException
      * drop(1)
      * }
      */
    val a = List(1, 2, 3)
    logger.info(s"a.headOption=${a.headOption}")
    logger.info(s"a.tail=${a.tail}")
    logger.info(s"a(2)=${a(2)}")

  }

  logger.info("------------------------> block line [4] <----------------------------")

  {
    val a = List(1, 3, 5, 7, 9)

    logger.info(s"a.length=${a.length}")
    logger.info(s"a.reverse=${a.reverse}")
    logger.info(s"a.reverse=${a.map(_ * 2)}")
    logger.info(s"a.map(_ % 3 == 0)=${a.map(_ % 3 == 0)}")
    logger.info(s"a.filter(_ % 3 == 0)=${a.filter(_ % 3 == 0)}")

    logger.info(s"a.reduceLeft(_ + _)=${a.reduceLeft(_ + _)}")
    logger.info(s"a.reduceLeft(_ * _)=${a.reduceLeft(_ * _)}")

    // foldLeft就像reduce，但具有明确的起始值：
    logger.info(s"a.foldLeft(1)(_ + _)=${a.foldLeft(1)(_ + _)}")

  }

  logger.info("------------------------> block line [5] <----------------------------")

  {
    val a = (1 to 5).toList
    logger.info(s"a=$a")
    logger.info(s"a=${0 :: a}")

    val head = List(1, 3)
    val tail = List(5, 7)
    logger.info(s"head ::: tail=${head ::: tail}")
    logger.info(s"head ::: Nil=${head ::: Nil}")
  }

  {
    val cond: (Int, Int) => Boolean = (_ < _)

    def insert(x: Int, xs: List[Int]): List[Int] =
      xs match {
        case List() => x :: Nil: List[Int]
        case y :: ys => if (cond(x, y)) x :: y :: ys else y :: insert(x, ys)
      }

    logger.info(s"insert(2, 1 :: 3 :: Nil)=${insert(2, 1 :: 3 :: Nil)}")
    logger.info(s"insert(1, 2 :: 3 :: Nil)=${insert(1, 2 :: 3 :: Nil)}")
    logger.info(s"insert(3, 1 :: 2 :: Nil)=${insert(3, 1 :: 2 :: Nil)}")

    //    insert(2, 1 :: 3 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
    //    insert(1, 2 :: 3 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
    //    insert(3, 1 :: 2 :: Nil) shouldBe (1 :: 2 :: 3 :: Nil)
  }
}
