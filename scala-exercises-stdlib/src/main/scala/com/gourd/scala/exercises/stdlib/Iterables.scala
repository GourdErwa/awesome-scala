package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

import scala.collection.immutable.Stream.cons
import scala.language.postfixOps

/** Iterables
  * [[Iterable]]
  *
  * @author Li.Wei by 2019/8/22
  */
object Iterables {
  private val logger = LoggerFactory.getLogger("Iterables")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")


  { //
    val list = List(3, 5, 9, 11, 15, 19, 21)
    val it = list.iterator
    if (it.hasNext) {
      logger.info(s"it.next=${it.next}")
    }
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { //
    val list = List(3, 5, 9, 11, 15, 19, 21, 24, 32)

    val grouped: Iterator[List[Int]] = list grouped 3 // 固定大小的Iterable
    logger.info(s"grouped.next=${grouped.next}")
    logger.info(s"grouped.next=${grouped.next}")
    logger.info(s"grouped.next=${grouped.next}")

    val sliding: Iterator[List[Int]] = list sliding 3 // 滑动窗口的Iterable
    logger.info(s"sliding.next=${sliding.next}")
    logger.info(s"sliding.next=${sliding.next}")
    logger.info(s"sliding.next=${sliding.next}")

    val slidingStep: Iterator[List[Int]] = list sliding(3, 3) // 滑动窗口的Iterable
    logger.info(s"slidingStep.next=${slidingStep.next}")
    logger.info(s"slidingStep.next=${slidingStep.next}")
    logger.info(s"slidingStep.next=${slidingStep.next}")

    val takeRight = list takeRight 3
    logger.info(s"takeRight=$takeRight")

    val dropRight = list dropRight 3
    logger.info(s"dropRight=$dropRight")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // zip 方法
    // zip 将两个迭代器拼接成来自两个迭代器的可迭代的对应元素对。
    // 例如Iterable(x1, x2, x3) zip Iterable(y1, y2, y3)将返回((x1, y1), (x2, y2), (x3, y3))：
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann", "Stella")
    logger.info(s"(xs zip ys)=${xs zip ys}")

    // 如果两个Iterables的大小不同，那么zip只会压缩可以配对的内容。
    // 例如Iterable(x1, x2, x3) zip Iterable(y1, y2)将返回((x1, y1), (x2, y2))：
    val ys1 = List("Bob", "Ann")
    logger.info(s"(xs zip ys1)=${xs zip ys1}")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { // zipAll 方法
    // 如果两个Iterables尺寸不同，zipAll则可以为填充物提供无法找到的补充。
    // 例如Iterable(x1, x2, x3) zipAll (Iterable(y1, y2), x, y)将返回((x1,y1), (x2, y2), (x3, y)))：
    val xs = List(3, 5, 9)
    val ys = List("Bob", "Ann")

    logger.info(s"""(xs zipAll (ys, -1, "?"))=${xs zipAll(ys, -1, "?")}""")
    val xt = List(3, 5)
    val yt = List("Bob", "Ann", "Stella")

    logger.info(s"""(xt zipAll(yt, -1, "?"))=${xt zipAll(yt, -1, "?")}""")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { // zipWithIndex 将Iterable使用整数索引压缩：
    val xs = List("Manny", "Moe", "Jack")
    logger.info(s"xs.zipWithIndex=${xs.zipWithIndex}")
  }

  logger.info("------------------------> block line [6] <----------------------------")

  { // sameElements 如果两者Iterables以相同的顺序生成相同的元素，则返回true ：
    val xs = List("Manny", "Moe", "Jack")
    val ys = List("Manny", "Moe", "Jack")
    logger.info(s"xs sameElements ys=${xs == ys}")

    val xt = List("Manny", "Moe", "Jack")
    val yt = List("Manny", "Jack", "Moe")
    logger.info(s"xt sameElements yt=${xt == yt}")

    val xs1 = Set(3, 2, 1, 4, 5, 6, 7)
    val ys1 = Set(7, 2, 1, 4, 5, 6, 3)
    logger.info(s"xs1.iterator sameElements ys1.iterator=${xs1.iterator sameElements ys1.iterator}")

    val xt1 = Set(1, 2, 3) // Set3 固定顺序
    val yt1 = Set(3, 2, 1)
    logger.info(s"xt1.iterator sameElements yt1.iterator=${xt1.iterator sameElements yt1.iterator}")
  }

  logger.info("------------------------> block line [7] <----------------------------")

  { // ++将两个加 IterableOnce 在一起。结果 IterableOnce 与第一个元素的类型相同
    val set = Set(1, 9, 10, 22)
    val list = List(3, 4, 5, 10)
    val result: Set[Int] = set ++ list
    logger.info(s"result=$result")
    val result2: Seq[Int] = list ++ set
    logger.info(s"result2=$result2")

    val lastOption = Set(1, 3, 4, 6).map(_ * 4).lastOption
    logger.info(s"lastOption=$lastOption")

    val flatten = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10)).flatten
    logger.info(s"flatten=$flatten")

    // flatMap=flatten+map
    val flatMap = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10)).flatMap(_.map(_ * 4))
    logger.info(s"flatMap=$flatMap")

    // flatMap的Options将过滤掉所有None，但保留Some：
    val flatMapOption = List(1, 2, 3, 4, 5).flatMap(it => if (it % 2 == 0) Some(it) else None)
    logger.info(s"flatMapOption=$flatMapOption")

    /**
      * [[PartialFunction]]
      * 练习示例[[PartialFunctions]]
      */
    val collect = List(4, 6, 7, 8, 9, 13, 14).collect { // PartialFunction 偏函数
      case x: Int if x % 2 == 0 => x * 3
    }
    logger.info(s"collect=$collect")

  }

  logger.info("------------------------> block line [8] <----------------------------")

  { // take
    //val lazyList = LazyList(4, 6, 7, 8, 9, 13, 14)
    val list = List(4, 6, 7, 8, 9, 13, 14)
    val result = list.toStream
    logger.info(s"result.isInstanceOf[Stream[_]]=${result.isInstanceOf[Stream[_]]}")
    logger.info(s"(lazyList take 3)=${result take 3}") // Stream(4,6,7)
  }

  logger.info("------------------------> block line [9] <----------------------------")

  { // hasDefiniteSize
    val map = Map("Phoenix" -> "Arizona", "Austin" -> "Texas")
    logger.info(s"map.hasDefiniteSize=${map.hasDefiniteSize}")
    val stream = cons(0, cons(1, Stream.empty))
    logger.info(s"stream.hasDefiniteSize=${stream.hasDefiniteSize}")

    // find find将找到的谓词相匹配的第一项为Some，如果元件被未找到为None：
    val list = List(10, 19, 45, 1, 22)
    logger.info(s"list.find(_ % 2 != 0)=${list.find(_ % 2 != 0)}")
    val list2 = List(4, 8, 16)
    logger.info(s"list2.find(_ % 2 != 0)=${list2.find(_ % 2 != 0)}")
  }

  logger.info("------------------------> block line [10] <----------------------------")

  { // tail init slice take forall exists count
    val list = List(10, 19, 45, 1, 22)
    logger.info(s"list.tail=${list.tail}") // tail 将返回没有头部的其余集合
    logger.info(s"list.init=${list.init}") // init 将返回没有最后一个的集合的其余部分
    logger.info(s"list.slice(1, 3)=${list.slice(1, 3)}") // 给定from索引和to索引slice将返回集合的一部分，包括from，并排除to：
    logger.info(s"list.take(3)=${list.take(3)}") // take 将返回给定的前N个元素数：
    logger.info(s"list forall (_ < 100)=${list forall (_ < 100)}") // forall 条件是否对所有成员满足
    logger.info(s"list exists (_ < 100)=${list exists (_ < 100)}") // exists 条件对某些成员满足
    logger.info(s"list count (_ < 100)=${list count (_ < 100)}") // count 条件对某些成员满足的数量

  }

  logger.info("------------------------> block line [11] <----------------------------")

  { // splitAt span partition
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)
    logger.info(s"array splitAt 3=${array splitAt 3}") // splitAt 也被定义为(xs take n, xs drop n)
    logger.info(s"array span (_ < 100)=${array span (_ < 100)}") // span 也被定义为(xs takeWhile p, xs dropWhile p)
    logger.info(s"array partition (_ < 100)=${array partition (_ < 100)}") // partition 也被定义为(xs filter p, xs filterNot p)
  }
  { // groupBy 将集合按条件分组，转换为 map 结构
    val array = Array(87, 44, 5, 4, 200, 10, 39, 100)

    val oddAndSmallPartial: PartialFunction[Int, String] = {
      case x: Int if x % 2 != 0 && x < 100 => "Odd and less than 100"
    }

    val evenAndSmallPartial: PartialFunction[Int, String] = {
      case x: Int if x != 0 && x % 2 == 0 && x < 100 => "Even and less than 100"
    }

    val negativePartial: PartialFunction[Int, String] = {
      case x: Int if x < 0 => "Negative Number"
    }

    val largePartial: PartialFunction[Int, String] = {
      case x: Int if x > 99 => "Large Number"
    }

    val zeroPartial: PartialFunction[Int, String] = {
      case x: Int if x == 0 => "Zero"
    }

    val groupBy: Map[String, Array[Int]] = array groupBy {
      oddAndSmallPartial orElse
        evenAndSmallPartial orElse
        negativePartial orElse
        largePartial orElse
        zeroPartial
    }

    logger.info(s"""(result("Even and less than 100") size)=${groupBy("Even and less than 100").length}""")
    logger.info(s"""(result("Large Number") size)=${groupBy("Large Number").length}""")
  }

  logger.info("------------------------> block line [12] <----------------------------")

  { // foldLeft 初始值init，操作op，foldRight定义为：x1 op (x2 op (x3 op (x4 op init)))
    // foldRight 初始值init，操作op，foldRight定义为：x1 op (x2 op (x3 op (x4 op init)))
    val list = List(5, 4, 3, 2, 1)

    val result = (list foldLeft 0) ((`running total`, `next element`) =>
      `running total` - `next element`
    )
    logger.info(s"result=$result")
    val result2 = (list foldRight 0) { (`running total`, `next element`) =>
      `running total` - `next element`
    }
    logger.info(s"result2=$result2")

    val result3 = (list foldLeft 0) (_ - _) //Short hand
    logger.info(s"result3=$result3")
    val result4 = (list foldRight 0) (_ - _)
    logger.info(s"result4=$result4")

    logger.info(s"(((((0 - 5) - 4) - 3) - 2) - 1)=${(((((0 - 5) - 4) - 3) - 2) - 1)}") // foldLeft
    logger.info(s"(5 - (4 - (3 - (2 - (1 - 0)))))=${(5 - (4 - (3 - (2 - (1 - 0)))))}") // foldRight
  }

  logger.info("------------------------> block line [13] <----------------------------")

  { // reduceLeft 类似于foldLeft，除了种子是头部值
    // reduceRight 类似于foldRight，除了种子是最后一个值：
    val intList = List(5, 4, 3, 2, 1)
    logger.info(s"intList.reduceLeft1=${intList.reduceLeft(_ + _)}")
    logger.info(s"intList.reduceRight=${intList.reduceRight(_ + _)}")

    val stringList = List("Do", "Re", "Me", "Fa", "So", "La", "Te")
    logger.info(s"stringList.reduceLeft1=${stringList.reduceLeft(_ + _)}")
    logger.info(s"stringList.reduceRight=${stringList.reduceRight(_ + _)}")

    // (1 - (2 - (3 - (4 - 5)))) = 3
    logger.info(s"intList.reduceRight((x, y) => x - y)=${intList.reduceRight((x, y) => x - y)}")
    logger.info(s"intList.reverse.reduceLeft((x, y) => y - x)=${intList.reverse.reduceLeft((x, y) => y - x)}")

    // def reduce[B >: A](op: (B, B) => B): B = reduceLeft(op)
    logger.info(s"intList.reverse.reduce((x, y) => y - x)=${intList.reverse.reduce((x, y) => y - x)}")
  }

  logger.info("------------------------> block line [14] <----------------------------")

  { // transpose
    //  e.g.: ((x1, x2),(y1, y2)).transpose = (x1, y1), (x2, y2)
    //  e.g.: ((x1, x2, x3),(y1, y2, y3),(z1, z2, z3)).transpose = ((x1, y1, z1), (x2, y2, z2), (x3, y3, z3))
    val list = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    logger.info(s"list.transpose=${list.transpose}")

    val list2 = List(List(1), List(4))
    logger.info(s"list2.transpose=${list2.transpose}")

    val list3 = List(List(1, 2), List(4)) // transpose requires all collections have the same size
    // logger.info(s"list3.transpose=${list3.transpose}")
  }
}
