package com.gourd.scala.base

/**
  * 类
  * @author Li.Wei by 2019-08-06
  */
package object classes {

  /**
    * 类定义
    * 一个最简的类的定义就是关键字class+标识符，类名首字母应大写。
    * 关键字new被用于创建类的实例。
    * 类可以在抽象类中定义。
    */
  {
    // 不包含构造器和类体
    class User

    val user1 = new User

    // 包含构造器和类体
    class Point(var x: Int, var y: Int) {
      def move(dx: Int, dy: Int): Unit = {
        x = x + dx
        y = y + dy
      }

      override def toString: String =
        s"($x, $y)"
    }

    val point1 = new Point(2, 3)
    point1.x // 2
    println(point1) // prints (2, 3)

    // 抽象类中定义类
    abstract class Soldier(val first: String, val last: String) {

      class Catch(val number: Long) {
        // nothing to do here.  Just observe that it compiles
      }

    }

    // squadron为Pilot中自己的成员变量，不是继承父类的成员变量
    class Pilot(override val first: String, override val last: String, val squadron: Long)
      extends Soldier(first, last)

    val pilot = new Pilot("John", "Yossarian", 256)
    val catchNo = new pilot.Catch(22)
  }

  /**
    * 构造器
    * 构造器可以通过提供一个默认值来拥有可选参数。
    * 构造器是从左往右读取参数，所以如果仅仅要传某个参数的值，你需要带名传参。
    */
  {
    class Point(var x: Int = 0, var y: Int = 0)

    // 使用默认值创建
    val origin = new Point // x and y are both set to 0
    // 从左往右读取参数，覆盖x参数的默认值
    val point1 = new Point(1)
    println(point1.x) // prints 1
    // 单独传入y的值，由于y位于第二个参数，因此需要带名传参
    val point2 = new Point(y = 2)
    println(point2.y) // prints 2
  }

  /**
    * 私有成员和Getter/Setter语法
    * 成员默认是公有(public)的。使用private访问修饰符可以在类外部隐藏它们。
    * scala私有成员变量getter和setter方法的特殊定义形式：value和value_= 详情见示例。
    * 主构造方法中带有val和var的参数是公有的。然而val是不可变的,类创建后不可以重新赋值。
    * 不带val或var的参数是私有的，仅在类中可见。
    */
  {
    class Point {
      private var _x = 0
      // 阈值
      private val bound = 100
      // getter方法
      def x = _x
      // setter的特殊定义语法，getter方法后面跟上_=(参数)
      def x_= (newValue: Int): Unit = {
        if (newValue < bound) _x = newValue else printWarning
      }

      private def printWarning = println("WARNING: Out of bounds")
    }
    val point1 = new Point
    point1.x = 99  // 调用setter方法赋值
    println(point1.x)  // 99
    point1.x = 101 // prints the warning

    // val定义成员变量，类创建后，不可以重新为成员变量赋值
    class Point(val x: Int, val y: Int)
    val point = new Point(1, 2)
    point.x = 3  // <-- does not compile
    // 成员变量私有，外部不可以访问，只在类中可见
    class Point(x: Int, y: Int)
    val point = new Point(1, 2)
    point.x  // <-- does not compile
  }

  /**
    * 单例对象
    * 使用关键字object定义
    */
  {
    object Logger {
      def info(message: String): Unit = println(s"INFO: $message")
    }

    Logger.info("静态方法，直接调用")
  }

  /**
    * 伴生对象
    * 当一个单例对象和某个类共享一个名称时，这个单例对象称为伴生对象。同理，这个类被称为是这个单例对象的伴生类。
    * 类和它的伴生对象可以互相访问其私有成员。使用伴生对象来定义那些在伴生类中不依赖于实例化对象而存在的成员变量或者方法。
    */
  {

  }
}