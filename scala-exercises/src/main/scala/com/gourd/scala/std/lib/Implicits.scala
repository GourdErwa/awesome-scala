package com.gourd.scala.std.lib

import org.slf4j.LoggerFactory

/** Implicits 隐式参数
  * https://www.scala-exercises.org/std_lib/implicits
  *
  * @author Li.Wei by 2019/8/22
  */
object Implicits {
  private val logger = LoggerFactory.getLogger("Implicits")

  def main(args: Array[String]): Unit = {

    logger.info("------------------------> block line [1] <----------------------------")

    { //
      class KoanIntWrapper(val original: Int) {
        def isOdd = original % 2 != 0
      }

      implicit def thisMethodNameIsIrrelevant(value: Int): KoanIntWrapper = new KoanIntWrapper(value)

      logger.info(s"19.isOdd=${19.isOdd}")
      logger.info(s"20.isOdd=${20.isOdd}")
    }

    logger.info("------------------------> block line [2] <----------------------------")

    { //
      object MyPredef {

        class KoanIntWrapper(val original: Int) {
          def isOdd = original % 2 != 0

          def isEven = !isOdd
        }

        implicit def thisMethodNameIsIrrelevant(value: Int): MyPredef.KoanIntWrapper = new KoanIntWrapper(value)
      }
      import MyPredef._
      //imported implicits come into effect within this scope
      logger.info(s"19.isOdd=${19.isOdd}")
      logger.info(s"20.isOdd=${20.isOdd}")
    }

    logger.info("------------------------> block line [3] <----------------------------")

    { //
      import java.math.BigInteger
      implicit def Int2BigIntegerConvert(value: Int): BigInteger = new BigInteger(value.toString)

      def add(a: BigInteger, b: BigInteger) = a.add(b)

      logger.info(s"add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9)=${
        add(Int2BigIntegerConvert(3), Int2BigIntegerConvert(6)) == Int2BigIntegerConvert(9)
      }")
      logger.info(s"add(3, 6) == 9=${add(3, 6) == 9}")
      logger.info(s"add(3, 6) == Int2BigIntegerConvert(9)=${add(3, 6) == Int2BigIntegerConvert(9)}")
      logger.info(s"add(3, 6) == (9: BigInteger)=${add(3, 6) == (9: BigInteger)}")
      logger.info(s"add(3, 6).intValue == 9 =${add(3, 6).intValue == 9}")
    }

    logger.info("------------------------> block line [4] <----------------------------")

    { //
      def howMuchCanIMake_?(hours: Int)(implicit dollarsPerHour: BigDecimal) = dollarsPerHour * hours

      implicit val hourlyRate: BigDecimal = BigDecimal(34)
      logger.info(s"howMuchCanIMake_?(30)=${howMuchCanIMake_?(30)}")
    }

    logger.info("------------------------> block line [5] <----------------------------")

    {
      def howMuchCanIMake_?(hours: Int)(implicit amount: BigDecimal, currencyName: String) =
        (amount * hours).toString() + " " + currencyName

      implicit val hourlyRate: BigDecimal = BigDecimal(34)
      implicit val currencyName: String = "Dollars"

      logger.info(s"howMuchCanIMake_?(30)=${howMuchCanIMake_?(30)}")
    }

    logger.info("------------------------> block line [6] <----------------------------")

    { // 默认参数优先于隐式函数参数
      def howMuchCanIMake_?(hours: Int, amount: BigDecimal = 34, currencyName: String = "Dollars") =
        (amount * hours).toString() + " " + currencyName

      logger.info(s"howMuchCanIMake_?(30)=${howMuchCanIMake_?(30)}")
      logger.info(s"howMuchCanIMake_?(30, 95)=${howMuchCanIMake_?(30, 95)}")
    }
  }
}
