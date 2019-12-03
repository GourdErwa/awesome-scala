package com.gourd.scala.base.examples

import com.gourd.scala.MainApp

/** [[Either]]
  * 程序设计中经常会有这样的需求，一个函数（或方法）在传入不同参数时会返回不同的值。
  * 返回值是两个不相关的类型，分别为： Left 和 Right 。
  * 惯例中我们一般认为 Left 包含错误或无效值， Right包含正确或有效值。
  *
  * @author Li.Wei by 2019-08-06
  */
object ExampleEither extends MainApp {

  type Rectangle2 = (Double, Double)
  type Circle2 = Double
  type Shape2 = Either[Rectangle2, Circle2]

  val rect2: Shape2 = Left((3.0, 4.0))
  val circ2: Shape2 = Right(1.0)

  def area2(shape: Shape2): Double =
    shape match {
      case Left((w, h)) => w * h
      case Right(r) => math.Pi * r * r
    }

  area2(rect2)
  // res4: Double = 12.0

  area2(circ2)
  // res5: Double = 3.141592653589793
}
