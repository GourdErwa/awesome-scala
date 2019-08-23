package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/** Traits
  * https://www.scala-exercises.org/std_lib/Traits
  *
  * @author Li.Wei by 2019/8/22
  */
object Traits {
  private val logger = LoggerFactory.getLogger("Traits")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    case class Event(name: String)

    trait EventListener {
      def listen(event: Event): String
    }

    class MyListener extends EventListener {
      def listen(event: Event): String =
        event match {
          case Event("Moose Stampede") => "An unfortunate moose stampede occurred"
          case _ => "Nothing of importance occurred"
        }
    }

    val evt = Event("Moose Stampede")
    val myListener = new MyListener

    logger.info(s"myListener.listen(evt)=${myListener.listen(evt)}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { // 一个类只能从一个类或特征扩展，任何后续扩展都应该使用关键字with
    case class Event(name: String)

    trait EventListener {
      def listen(event: Event): String
    }

    class OurListener

    class MyListener extends OurListener with EventListener {
      def listen(event: Event): String =
        event match {
          case Event("Woodchuck Stampede") => "An unfortunate woodchuck stampede occurred"
          case _ => "Nothing of importance occurred"
        }
    }

    val evt = Event("Woodchuck Stampede")
    val myListener = new MyListener
    logger.info(s"myListener.listen(evt)=${myListener.listen(evt)}")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // 特征是多态的。如果通过扩展名相关，任何类型都可以通过其他类型引用：
    case class Event(name: String)

    trait EventListener {
      def listen(event: Event): String
    }

    class MyListener extends EventListener {
      def listen(event: Event): String =
        event match {
          case Event("Moose Stampede") => "An unfortunate moose stampede occurred"
          case _ => "Nothing of importance occurred"
        }
    }

    val myListener = new MyListener
    logger.info(s"myListener.isInstanceOf[MyListener]=${myListener.isInstanceOf[MyListener]}")
    logger.info(s"myListener.isInstanceOf[EventListener]=${myListener.isInstanceOf[EventListener]}")
    logger.info(s"myListener.isInstanceOf[Any]=${myListener.isInstanceOf[Any]}")
    logger.info(s"myListener.isInstanceOf[AnyRef]=${myListener.isInstanceOf[AnyRef]}")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { // 自我类型
    trait B {
      def id: Int = 2

      def bId: Int = 2
    }

    trait A {
      self: B =>

      override def id = self.bId + 1

      def aId = 1
    }

    //val a = new A  //***does not compile!!!***
    val obj = new A with B {
      override def id = 11
    }
    logger.info(s"(obj.aId + obj.bId)=${obj.aId + obj.bId}")
    logger.info(s"(obj.id + obj.bId)=${obj.id + obj.bId}")
  }

}
