package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

import scala.language.postfixOps

/** 中缀前缀和后缀操作符
  * https://www.scala-exercises.org/std_lib/infix_prefix_and_postfix_operators
  *
  * Scala的操作符不是特殊的语言语法, 任何方法都可以是操作符
  * 操作符分为前缀, 中缀, 后缀
  * Scala中所有操作符都是方法调用
  *
  * 中缀操作符的两个操作数, 一个在左一个在右
  * 前缀操作符方法名在操作符上加了unary_前缀(+, -, !, ~)
  * 后缀操作符是不用点或括号调用的不带任何参数的方法
  *
  * 算术操作符: +, -, *, /, %
  * 关系, 逻辑和位操作: >, <, >=, <=, ==, !=, &&, ||, &, |, ^, ~(反码)
  * 位移操作: <<, >>, >>>(无符号右移)
  *
  * 任何不需要参数的方法都可以用作后缀运算符：a.m 可以写成 a m
  * 例如，a.+(b)相当于 a + b 和 a.! 相同 a!
  * 后缀运算符的优先级低于中缀运算符，因此：
  * foo bar baz         相当于 foo.bar(baz)。
  * foo bar baz bam     相当于 (foo.bar(baz)).bam
  * foo bar baz bam bim 相当于 (foo.bar(baz)).bam(bim)
  *
  * @author Li.Wei by 2019/8/22
  */
object InfixPrefixPostfixOperators {
  private val logger = LoggerFactory.getLogger("InfixPrefixPostfixOperators")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    val g: Int = 3
    logger.info(s"(g + 4)=${g + 4}") // + 是一个中缀运算符
    logger.info(s"g.+(4)=${g.+(4)}")

    val w: Int = 31
    logger.info(s"w toHexString=${w toHexString}")

    // 如果对象的方法名以unary_开头，前缀操作符就可以工作:
    val z: Int = 31
    logger.info(s"(-z)=${-z}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { // 中缀运算符
    val g: String = "Check out the big brains on Brad!"

    //indexOf(Char) 中缀运算符
    logger.info(s"g indexOf 'o'=${g indexOf 'o'}")

    // g indexOf 'o', 4 should be (6) //indexOf(Char, Int) 如果对象具有带两个参数的方法，则中缀运算符不起作用：
    logger.info(s"g.indexOf('o', 7)=${g.indexOf('o', 7)}")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // 前缀运算符
    // 以下是为我们自己的类创建前缀运算符的方法。可以用来作为前缀唯一的标识符+，-，!和~：
    class Stereo {
      def unary_+ = "on"

      def unary_- = "off"
    }

    val stereo = new Stereo
    logger.info(s"(-stereo)=${-stereo}")
    logger.info(s"(+stereo)=${+stereo}")
  }
}
