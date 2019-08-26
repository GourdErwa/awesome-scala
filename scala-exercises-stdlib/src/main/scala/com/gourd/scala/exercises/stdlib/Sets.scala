package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/** [[Set]]
  * https://www.scala-exercises.org/std_lib/sets
  * Set是Iterable不包含重复元素的
  *
  * @author Li.Wei by 2019-08-21
  */
object Sets {
  private val logger = LoggerFactory.getLogger("Sets")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    logger.info(s"mySet.size=${mySet.size}")

    val aNewSet = mySet + "Illinois"
    logger.info(s"""mySet.contains("Illinois")=${mySet.contains("Illinois")}""")
    logger.info(s"""aNewSet.contains("Illinois")=${aNewSet.contains("Illinois")}""")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  {
    val mySet = Set("Michigan", "Ohio", 12)

    logger.info(s"""mySet.contains(12)=${mySet.contains(12)}""")
    logger.info(s"""mySet(12)=${mySet(12)}""")
    logger.info(s"""mySet.contains("UI")=${mySet.contains("UI")}""")

    val aNewSet = mySet - "Michigan"
    logger.info(s"""mySet.contains("Michigan")=${mySet.contains("Michigan")}""")
    logger.info(s"""aNewSet.contains("Michigan")=${aNewSet.contains("Michigan")}""")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  {
    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet = mySet - "Minnesota"
    logger.info(s"""aNewSet.equals(mySet)=${aNewSet.equals(mySet)}""")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  {
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Minnesota")
    // NOTE: Scala 2.7 used **, deprecated for & or intersect in Scala 2.8

    logger.info(s"""交集=${(mySet1 & mySet2).equals(Set("Michigan", "Wisconsin"))}""")
    logger.info(s"""并集=${mySet1 | mySet2}""")
    logger.info(s"""子集=${mySet1 subsetOf mySet2}""")
    logger.info(s"""差集=${mySet1 &~ mySet2}""")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { // 等效性与顺序无关
    val mySet1 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet2 = Set("Wisconsin", "Michigan", "Ohio", "Iowa")

    logger.info(s"""mySet1.equals(mySet2)=${mySet1.equals(mySet2)}""")
  }

}
