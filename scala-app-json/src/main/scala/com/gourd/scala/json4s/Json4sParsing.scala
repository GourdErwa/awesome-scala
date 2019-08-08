package com.gourd.scala
package json4s

import org.slf4j.LoggerFactory

/** Parsing JSON => JValue
  *
  * @author Li.Wei by 2019-08-08
  */
object Json4sParsing {

  private val logger = LoggerFactory.getLogger("Json4sParsing")

  def main(args: Array[String]): Unit = {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // String parse
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    {
      import org.json4s._
      import org.json4s.jackson.JsonMethods._
      logger.info(parse("""{ "numbers" : [1, 2, 3, 4] }""").toString)
      // JObject(List((numbers,JArray(List(JInt(1), JInt(2), JInt(3), JInt(4))))))

      logger.info(parse("""{"name":"Toy","price":35.35}""", useBigDecimalForDouble = true).toString)
      // JObject(List((name,JString(Toy)), (price,JDecimal(35.35))))
    }

  }

}
