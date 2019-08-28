package com.gourd.scala.cats

import org.slf4j.LoggerFactory

/**
  * https://typelevel.org/cats/typeclasses/eq.html
  *
  * @author Li.Wei by 2019-08-06
  */
object EqExample {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}
  {
    import cats.implicits._
    1 === 1
    "Hello" =!= "World"
  }
  {
    import cats.implicits._
    import cats.kernel.Eq

    case class Foo(a: Int, b: String)

    implicit val eqFoo: Eq[Foo] = Eq.fromUniversalEquals

    Foo(10, "") === Foo(10, "")
  }

}