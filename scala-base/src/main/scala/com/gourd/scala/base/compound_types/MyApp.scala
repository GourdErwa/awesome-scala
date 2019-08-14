package com.gourd.scala.base.compound_types

import org.slf4j.LoggerFactory

/** 复合类型
  *
  * 有时需要表明一个对象的类型是其他几种类型的子类型。 在 Scala 中，这可以表示成 复合类型，即多个类型的交集
  * 复合类型可以由多个对象类型构成，这些对象类型可以有单个细化，用于缩短已有对象成员的签名。 格式为：A with B with C ... { refinement }
  *
  * @author Li.Wei by 2019-08-06
  */
trait Cloneable extends java.lang.Cloneable {
  override def clone(): Cloneable = {
    super.clone().asInstanceOf[Cloneable]
  }
}

trait Resetable {
  def reset(): Unit
}

object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  /**
    * 将 obj 的类型同时指定为 Cloneable 和 Resetable。 这种复合类型在 Scala 中写成：Cloneable with Resetable。
    *
    * @param obj obj
    * @return Cloneable
    */
  def cloneAndReset(obj: Cloneable with Resetable): Cloneable = {
    val cloned = obj.clone()
    obj.reset()
    cloned
  }

  def main(args: Array[String]): Unit = {}
}
