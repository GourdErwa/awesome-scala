package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

import scala.language.postfixOps

/** 中缀类型
  *
  * 中缀类型 T1 op T2 由一个中缀运算符组成，该运算符 op 应用于两个类型的操作数 T1 和 T2。该类型等同于类型应用程序 op[T1,T2]。
  *
  * 中缀运算符op可以是任意标识符，除了 * 保留为表示重复参数类型的后缀修饰符。
  *
  * @author Li.Wei by 2019/8/22
  */
object InfixTypes {
  private val logger = LoggerFactory.getLogger("InfixTypes")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    case class Person(name: String)
    class Loves[A, B](val a: A, val b: B)

    // 注意方法参数类型
    def announceCouple(couple: Person Loves Person) = couple.a.name + " is in love with " + couple.b.name

    val romeo = Person("Romeo")
    val juliet = Person("Juliet")
    logger.info(s"announceCouple(new Loves(romeo, juliet))=${announceCouple(new Loves(romeo, juliet))}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { //
    case class Person(name: String) {
      def loves(person: Person): Loves[Person, Person] = new Loves(this, person)
    }

    class Loves[A, B](val a: A, val b: B)

    def announceCouple(couple: Person Loves Person) = couple.a.name + " is in love with " + couple.b.name

    val romeo = Person("Romeo")
    val juliet = Person("Juliet")
    logger.info(s"announceCouple(romeo loves juliet)=${announceCouple(romeo loves juliet)}")
  }
}
