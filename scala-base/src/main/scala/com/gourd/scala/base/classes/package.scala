package com.gourd.scala.base

/**
  * 类
  *
  * @author Li.Wei by 2019-08-06
  */
package object classes

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


///////////////////////////////////////////////////////////////////////////////////////////////////////
// 私有成员和Getter/Setter语法
///////////////////////////////////////////////////////////////////////////////////////////////////////
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

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 单例对象 使用关键字 object
///////////////////////////////////////////////////////////////////////////////////////////////////////
object Logger {
  def info(message: String): Unit = println(s"INFO: $message")
}

import scala.math._

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 伴生对象
///////////////////////////////////////////////////////////////////////////////////////////////////////
// 当一个单例对象和某个类共享一个名称时，这个单例对象称为 伴生对象。 同理，这个类被称为是这个单例对象的伴生类。
// 类和它的伴生对象可以互相访问其私有成员。使用伴生对象来定义那些在伴生类中不依赖于实例化对象而存在的成员变量或者方法。
case class Circle(radius: Double) {

  import Circle._

  def area: Double = calculateArea(radius)
}

object Circle {
  private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 类可以放在抽象类中
///////////////////////////////////////////////////////////////////////////////////////////////////////
abstract class Soldier(val firstName: String, val lastName: String) {

  class Catch(val number: Long) {
    // nothing to do here.  Just observe that it compiles
  }

}

class Pilot(override val firstName: String, override val lastName: String, val squadron: Long)
  extends Soldier(firstName, lastName)

//val pilot = new Pilot("John", "Yossarian", 256)
//val catchNo = new pilot.Catch(22)}