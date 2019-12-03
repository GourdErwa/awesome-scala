package com.gourd.scala.base.closures

import com.gourd.scala.MainApp

/**
  * 闭包
  * 闭包是函数，它的返回值取决于此函数之外声明一个或多个变量的值。
  *
  * @example
  * {{{
  * f(x) = x + i
  * }}}
  * @author Li.Wei by 2019-10-19
  */
object Example1 extends MainApp {

  // 以变量方式定义一个函数
  {
    val factor = 3
    val closures: Int => Int = (i: Int) => i * factor // 返回值依赖外部的变量factor
    logger.info(s"closures(10)=${closures(10)}")
  }

  // 以方法方式定义一个函数
  {
      val factor = 3

      def closures(i: Int): Int = factor * i

      val closuresFunc = closures(1)
      logger.info(s"closuresFunc=$closuresFunc")
    }

    // 以方法方式定义一个函数 ， 一旦 closures 函数初始化后就不依赖 i 变量 ，此时 factor 是未知的，一旦 factor 被捕获函数即 "关闭"
    {
      def closures(i: Int) = (factor: Int) => factor * i

      val closures1 = closures(1)
      val closures2 = closures(1)
      logger.info(s"closures1=${closures1(1)}")
      logger.info(s"closures2=${closures2(2)}")
    }
}
