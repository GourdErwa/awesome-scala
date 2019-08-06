package com.gourd.scala.base

/**
  * @author Li.Wei by 2019-08-06
  */
package object classes {

}

// 类定义
class Point1(var x: Int, var y: Int) {

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }

  override def toString: String = s"($x, $y)"
}

// val point1 = new Point1(2, 3)


// 提供一个默认值来拥有可选参数
class Point2(var x: Int = 0, var y: Int = 0)

// val point2 = new Point2(y=2)


// 不带val或var的参数是私有的，仅在类中可见
class Point3(x: Int, y: Int)

// val point = new Point3(1, 2)
// point.x  // <-- does not compile


// 私有成员和Getter/Setter语法
class Point4 {
  private var _x = 0
  private var _y = 0
  private val bound = 100

  // Getter
  def x: Int = _x

  // Setter
  def x_=(newValue: Int): Unit = {
    if (newValue < bound) _x = newValue else printWarning()
  }

  def y: Int = _y

  def y_=(newValue: Int): Unit = {
    if (newValue < bound) _y = newValue else printWarning()
  }

  private def printWarning(): Unit = println("WARNING: Out of bounds")
}
