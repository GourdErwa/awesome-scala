package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** [[Map]]
  * https://www.scala-exercises.org/std_lib/maps
  *
  * @author Li.Wei by 2019-08-21
  */
object Maps {
  private val logger = LoggerFactory.getLogger("Maps")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin1", "IA" -> "Iowa")
      .withDefaultValue("withDefaultValue")

    logger.info(s"myMap.size=${myMap.size}")

    val aNewMap = myMap + ("IL" -> "Illinois") + ("WI" -> "Wisconsin2")
    logger.info(s"aNewMap.size=${aNewMap.size}")
    logger.info(s"aNewMap.head=${aNewMap.head}")
    logger.info(s"aNewMap.last=${aNewMap.last}")
    // key 相同时 value值被替换
    logger.info(s"""aNewMap("WI")=${aNewMap("WI")}""")

    logger.info(s"""aNewMap("II")=${aNewMap("II")}""")
    logger.info(s"""aNewMap("II")=${aNewMap.getOrElse("II", "Miss value")}""")

  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")

    val aNewMap1 = myMap - ("MI", "WI") // Notice: single '-' operator for tuples
    val aNewMap2 = myMap -- List("IA", "OH")
    logger.info(s"""aNewMap.contains("OH")=${aNewMap1.contains("OH")}""")
    logger.info(s"""aNewMap2.contains("OH")=${aNewMap2.contains("OH")}""")

    val aNewMap3 = myMap -- List("II", "WW")
    logger.info(s"""myMap eq aNewMap3=${myMap eq aNewMap3}""")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  {
    val myMap1 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val myMap2 = Map("WI" -> "Wisconsin", "MI" -> "Michigan", "IA" -> "Iowa", "OH" -> "Ohio")
    logger.info(s"""myMap1.equals(myMap2)=${myMap1.equals(myMap2)}""")
  }

}
