package com.gourd.scala.exercises.stdlib

import org.slf4j.LoggerFactory

/**
  * 枚举
  * 创建枚举，请创建一个扩展抽象类的对象Enumeration，并val为该方法设置一个变量Value
  *
  * @author Li.Wei by 2019/8/22
  */
object Enumerations {
  private val logger = LoggerFactory.getLogger("App")

  def main(args: Array[String]): Unit = {}

  logger.info("------------------------> block line [1] <----------------------------")

  { //
    object Planets extends Enumeration {
      val Mercury = Value
      val Venus = Value
      val Earth = Value
      val Mars = Value
      val Jupiter = Value
      val Saturn = Value
      val Uranus = Value
      val Neptune = Value
      val Pluto = Value
    }

    logger.info(s"Planets.Mercury.id=${Planets.Mercury.id}")
    logger.info(s"Planets.Venus.id=${Planets.Venus.id}")

    logger.info(s"Planets.Mercury.toString=${Planets.Mercury.toString}")
    logger.info(s"Planets.Venus.toString=${Planets.Venus.toString}")

    logger.info(s"(Planets.Earth == Planets.Earth)=${(Planets.Earth == Planets.Earth)}")
    logger.info(s"(Planets.Neptune == Planets.Jupiter)=${(Planets.Neptune == Planets.Jupiter)}")
  }

  logger.info("------------------------> block line [2] <----------------------------")

  { // 使用自己的索引和自己的字符串创建枚举
    object GreekPlanets extends Enumeration {

      val Mercury = Value(1, "Hermes")
      val Venus = Value(2, "Aphrodite")
      //Fun Fact: Tellus is Roman for (Mother) Earth
      val Earth = Value(3, "Gaia")
      val Mars = Value(4, "Ares")
      val Jupiter = Value(5, "Zeus")
      val Saturn = Value(6, "Cronus")
      val Uranus = Value(7, "Ouranus")
      val Neptune = Value(8, "Poseidon")
      val Pluto = Value(9, "Hades")
    }
    logger.info(s"Planets.Mercury.id=${GreekPlanets.Mercury.id}")
    logger.info(s"Planets.Venus.id=${GreekPlanets.Venus.id}")

    logger.info(s"Planets.Mercury.toString=${GreekPlanets.Mercury.toString}")
    logger.info(s"Planets.Venus.toString=${GreekPlanets.Venus.toString}")

    logger.info(s"(Planets.Earth == Planets.Earth)=${(GreekPlanets.Earth == GreekPlanets.Earth)}")
    logger.info(s"(Planets.Neptune == Planets.Jupiter)=${(GreekPlanets.Neptune == GreekPlanets.Jupiter)}")
  }

  logger.info("------------------------> block line [3] <----------------------------")

  { // 一行中声明枚举Value
    object Planets extends Enumeration {
      val Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto = Value
    }
    logger.info(s"Planets.Mercury.id=${Planets.Mercury.id}")
    logger.info(s"Planets.Venus.id=${Planets.Venus.id}")

    logger.info(s"Planets.Mercury.toString=${Planets.Mercury.toString}")
    logger.info(s"Planets.Venus.toString=${Planets.Venus.toString}")

    logger.info(s"(Planets.Earth == Planets.Earth)=${(Planets.Earth == Planets.Earth)}")
    logger.info(s"(Planets.Neptune == Planets.Jupiter)=${(Planets.Neptune == Planets.Jupiter)}")
  }

  logger.info("------------------------> block line [4] <----------------------------")

  { // 枚举用字符串值声明
    object GreekPlanets extends Enumeration {

      val Mercury = Value("Hermes")
      val Venus = Value("Aphrodite")
      val Earth = Value("Gaia")
      val Mars = Value("Ares")
      val Jupiter = Value("Zeus")
      val Saturn = Value("Cronus")
      val Uranus = Value("Ouranus")
      val Neptune = Value("Poseidon")
      val Pluto = Value("Hades")
    }
    logger.info(s"Planets.Mercury.id=${GreekPlanets.Mercury.id}")
    logger.info(s"Planets.Venus.id=${GreekPlanets.Venus.id}")

    logger.info(s"Planets.Mercury.toString=${GreekPlanets.Mercury.toString}")
    logger.info(s"Planets.Venus.toString=${GreekPlanets.Venus.toString}")

    logger.info(s"(Planets.Earth == Planets.Earth)=${(GreekPlanets.Earth == GreekPlanets.Earth)}")
    logger.info(s"(Planets.Neptune == Planets.Jupiter)=${(GreekPlanets.Neptune == GreekPlanets.Jupiter)}")
  }

  logger.info("------------------------> block line [5] <----------------------------")

  { // 您可以Enumeration通过扩展Value类来扩展它。
    object Planets extends Enumeration {

      val G = 6.67300E-11

      class PlanetValue(val i: Int, val name: String, val mass: Double, val radius: Double)
        extends Val(i: Int, name: String) {

        def surfaceGravity = G * mass / (radius * radius)

        def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity

        def compare(that: PlanetValue) = this.i - that.i
      }

      val Mercury = new PlanetValue(0, "Mercury", 3.303e+23, 2.4397e6)
      val Venus = new PlanetValue(1, "Venus", 4.869e+24, 6.0518e6)
      val Earth = new PlanetValue(2, "Earth", 5.976e+24, 6.37814e6)
      val Mars = new PlanetValue(3, "Mars", 6.421e+23, 3.3972e6)
      val Jupiter = new PlanetValue(4, "Jupiter", 1.9e+27, 7.1492e7)
      val Saturn = new PlanetValue(5, "Saturn", 5.688e+26, 6.0268e7)
      val Uranus = new PlanetValue(6, "Uranus", 8.686e+25, 2.5559e7)
      val Neptune = new PlanetValue(7, "Neptune", 1.024e+26, 2.4746e7)
      val Pluto = new PlanetValue(8, "Pluto", 1.27e+22, 1.137e6)
    }
    logger.info(s"Planets.Earth.mass=${Planets.Earth.mass}") // 5.976E24
    logger.info(s"Planets.Earth.radius=${Planets.Earth.radius}") // 6378140.0
  }

}
