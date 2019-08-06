package com.gourd.scala.base.examples

import org.slf4j.LoggerFactory

/**
  * [[Either]]
  *
  * @author Li.Wei by 2019-08-06
  */
object ExampleEither {

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

  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {
    area2(rect2)
    // res4: Double = 12.0

    area2(circ2)
    // res5: Double = 3.141592653589793
  }
}
