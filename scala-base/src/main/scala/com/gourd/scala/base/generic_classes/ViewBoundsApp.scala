package com.gourd.scala.base.generic_classes

import com.gourd.scala.MainApp

/** 视图界定 View Bounds ， 目前已废弃该用法，推荐传入隐士函数进行转换
  *
  * 语法：
  * `[T <% Comparable[T]]`
  *
  * 说明：
  * <% :
  * 对上边界的加强版，可以利用implicit隐式转换将实参类型转换成目标类型 ,
  * <左侧的类型必须是右侧类的子类%表示如果左侧类型不是右侧的类型那么左侧的类型会隐式转换成右边的类型
  *
  * @author Li.Wei by 2019-08-07
  */
object ViewBoundsApp extends MainApp {

  def f[A](a: A, b: A)(implicit ev$1: A => Ordered[A]) = if (a < b) a else b

  // 等价于
  //    def f[A](a: A, b: A)(implicit ev$1: A => Ordered[A]) = if (a < b) a else b
}

