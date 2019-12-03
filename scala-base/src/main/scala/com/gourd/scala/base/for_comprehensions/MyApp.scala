package com.gourd.scala.base.for_comprehensions

import com.gourd.scala.MainApp

/**
  * Scala 提供一个轻量级的标记方式用来表示 序列推导。推导使用形式为 for (enumerators) yield e 的 for 表达式，
  *
  * 此处 enumerators 指一组以分号分隔的枚举器。一个 enumerator 要么是一个产生新变量的生成器，
  * 要么是一个过滤器。for 表达式在枚举器产生的每一次绑定中都会计算 e 值，并在循环结束后返回这些值组成的序列。
  *
  * 注意for表达式并不局限于使用列表。任何数据类型只要支持withFilter map，和flatMap 操作（不同数据类型可能支持不同的操作）都可以用来做序列推导。
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp extends MainApp {

  /*
  基础语法
  这里 for 循环后面使用的 yield 语句实际上会创建一个 List。因为当我们说 yield user.name 的时候，它实际上是一个 List[String]。
  user <- userBase 是生成器，if (user.age >=20 && user.age < 30) 是过滤器用来过滤掉那些年龄不是20多岁的人。
   */
  {
    case class User(name: String, age: Int)

    val base = Seq(User("Travis", 28), User("Kelly", 33), User("Jennifer", 44), User("Dennis", 23))
    val nonBasic = Seq(User("Li", 29), User("Kelly", 39), User("Jennifer", 47), User("Dennis", 21))

    // for 表达式实现
    val twentySomethingsFor = for {
      b <- base if b.age >= 20
      nb <- nonBasic if nb.age >= 10
      if b.name == nb.name
    } yield (b.name, b.age - nb.age)

    val twentySomethingsFlatMap = base // for 推导为 flatMap 表达式实现
      .withFilter(_.age >= 20)
      .flatMap {
        b =>
          nonBasic
            .withFilter(_.age >= 10)
            .withFilter(_.name == b.name)
            .map(nb => (b.name, b.age - nb.age))
      }

    logger.info(s"twentySomethingsFor=$twentySomethingsFor")
    logger.info(s"twentySomethingsFlatMap=$twentySomethingsFlatMap")
  }

  logger.info("> ------------------------------------------")

  // 计算了 0 到 n-1 的所有两两求和为 v 的数字的组合
  {
    def foo(n: Int, v: Int) = for (i <- 0 until n; j <- i until n if i + j == v) yield (i, j)

    foo(10, 10) foreach {
      case (i, j) => logger.info(s"($i, $j) ") // prints (1, 9) (2, 8) (3, 7) (4, 6) (5, 5)
    }
    /*
      这里 n == 10 和 v == 10。在第一次迭代时，i == 0 并且 j == 0 所以 i + j != v 因此没有返回值被生成。
      在 i 的值递增到 1 之前，j 的值又递增了 9 次。如果没有 if 语句过滤，上面的例子只会打印出如下的结果：
    (0, 0) (0, 1) (0, 2) (0, 3) (0, 4) (0, 5) (0, 6) (0, 7) (0, 8) (0, 9) (1, 1) ...
    */
  }

  logger.info("> ------------------------------------------")

  // 你可以在使用 for 表达式时省略 yield 语句。此时会返回 Unit。
  // 当你想要执行一些副作用的时候这很有用。下面的例子输出和上面相同的结果，但是没有使用 yield：
  {
    def foo(n: Int, v: Int): Unit =
      for (i <- 0 until n;
           j <- i until n if i + j == v)
        logger.info(s"($i, $j)")

    foo(10, 10)
  }

}