package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/**
  * 程序模板
  *
  * null
  * Scala null和Java一样。任何引用类型都可以是null字符串，对象或您自己的类。也像Java一样，像Ints这样的值类型不可能null。
  *
  * Null
  * Null是一个特殊的特征null。它是所有引用类型的子类型，但不是值类型的子类型。现有的目的是使它可以分配引用类型而不能赋值null类型。
  *
  * Nothing
  * 没有什么是保证零实例的特性。它是所有其他类型的子类型。它有两个主要原因：为永远不会正常返回的方法提供返回类型（即总是抛出异常的方法）。另一个原因是为Nil提供一种类型（下面解释）。
  *
  * Unit
  * Scala中的单位void与Java 相当。当函数没有返回值时，它在函数的签名中使用。
  *
  * Nil
  * Nil只是一个空列表，完全像是结果List()。它是类型List[Nothing]。由于我们知道没有Nothing的实例，我们现在有一个静态可验证的列表为空
  *
  * @author Li.Wei by 2019/8/22
  */
object EmptyValues {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    logger.info(s"List() == Nil=${(List() == Nil)}")
    logger.info(s"None == None=${None == None}")
    logger.info(s"None eq None=${None eq None}")
    logger.info(s"None.toList == Nil=${None.toList == Nil}")

    logger.info(s"None.asInstanceOf[Any] == None=${None.asInstanceOf[Any] == None}")
    logger.info(s"None.asInstanceOf[AnyRef] == None=${None.asInstanceOf[AnyRef] == None}")
    logger.info(s"None.asInstanceOf[AnyVal] == None=${None.asInstanceOf[AnyVal] == None}")

    logger.info(s"None.toList == Nil=${None.toList == Nil}")
    logger.info(s"None.toList == Nil=${None.toList == Nil}")

    val optional: Option[String] = None
    logger.info(s"optional.isEmpty=${optional.isEmpty}")
  }
  {
    logger.info(classOf[String].getCanonicalName)
    logger.info(classOf[String].getSimpleName)

    val e = '\"'
    val f = '\\'
    logger.info(s"${(f, e)}")
    logger.info("\"")
    logger.info("\\")

  }

}
