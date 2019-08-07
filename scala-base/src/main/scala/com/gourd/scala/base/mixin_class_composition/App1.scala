package com.gourd.scala.base.mixin_class_composition

import org.slf4j.LoggerFactory

/**
  * @author Li.Wei by 2019-08-06
  */
abstract class A {
  val message: String
}

class B extends A {
  val message = "I'm an instance of class B"
}

trait C extends A {
  def loudMessage: String = message.toUpperCase()
}

/**
  * 类D有一个父类B和一个混入C。一个类只能有一个父类但是可以有多个混入（分别使用关键字extend和with）。
  * 混入和某个父类可能有相同的父类。
  */
class D extends B with C

object App1 {
  private val logger = LoggerFactory.getLogger("App1")

  def main(args: Array[String]): Unit = {
    val d = new D
    logger.info(d.message) // I'm an instance of class B
    logger.info(d.loudMessage) // I'M AN INSTANCE OF CLASS B
  }
}