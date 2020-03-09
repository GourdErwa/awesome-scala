package com.gourd.scala.base.compound_types

import org.slf4j.LoggerFactory

/** 复合类型
  *
  * 有时需要表明一个对象的类型是其他几种类型的子类型。 在 Scala 中，这可以表示成 复合类型，即多个类型的交集
  * 复合类型可以由多个对象类型构成，这些对象类型可以有单个细化，用于缩短已有对象成员的签名。 格式为：A with B with C ... { refinement }
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 示例
    * 我们要编写一个方法cloneAndReset，此方法接受一个对象，克隆它并重置原始对象。
    */
  {
    trait Cloneable extends java.lang.Cloneable {
      override def clone(): Cloneable = {
        super.clone().asInstanceOf[Cloneable]
      }
    }
    trait Resetable {
      def reset: Unit
    }
    // 这里的obj类型应该是什么？如果是Cloneable则无法reset对象obj，如果是Resetable，则无法克隆obj
    /* def cloneAndReset(obj: ?): Cloneable = {
         val cloned = obj.clone()
         obj.reset
         cloned
       } */

    // 所以可以把obj的类型定义为复合类型--obj: Cloneable with Resetable
    def cloneAndReset(obj: Cloneable with Resetable): Cloneable = {
      val cloned = obj.clone()
      obj.reset
      cloned
    }
  }
}
