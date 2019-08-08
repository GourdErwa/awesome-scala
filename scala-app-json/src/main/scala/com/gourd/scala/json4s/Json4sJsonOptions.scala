package com.gourd.scala

package json4s

import org.json4s
import org.json4s.JsonAST.{JField, JInt, JObject, JString}
import org.json4s.jackson.JsonMethods._
import org.json4s.{Diff, JValue}
import org.slf4j.LoggerFactory

/** JSON 操作
  * - 合并与比较
  * - 查询
  *
  * @author Li.Wei by 2019-08-08
  */
object Json4sJsonOptions {

  private val logger = LoggerFactory.getLogger("Json4sJsonOptions")

  def main(args: Array[String]): Unit = {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // JSON 合并与比较
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    {
      val lotto1: JValue = parse(
        """{
         "lotto":{
           "lotto-id":5,
           "winning-numbers":[2,45,34,23,7,5,3],
           "winners":[{
             "winner-id":23,
             "numbers":[2,45,34,23,3,5]
           }]
         }
       }""")

      val lotto2: JValue = parse(
        """{
         "lotto":{
           "winners":[{
             "winner-id":54,
             "numbers":[52,3,12,11,18,22]
           }]
         }
       }""")
      val mergedLotto: json4s.JValue = lotto1 merge lotto2
      logger.info(s"json-merge:JValue=$mergedLotto")

      val diff: Diff = mergedLotto diff lotto1
      logger.info(s"json-merge:diff=$diff")
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // JSON 查询
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    {
      val json = parse(
        """
         { "name": "joe",
           "children": [
             {
               "name": "Mary",
               "age": 5
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)
      val r1: Seq[BigInt] = for {
        JObject(child) <- json // case class
        JField("age", JInt(age)) <- child // // 对象提取 unapply
      } yield age
      logger.info(s"json-search:Seq[BigInt]=$r1")

      val r2: List[(String, BigInt)] = for {
        JObject(child) <- json
        JField("name", JString(name)) <- child
        JField("age", JInt(age)) <- child
        if age > 4
      } yield (name, age)
      logger.info(s"json-search:List[(String, BigInt)]=$r2")

      logger.info((json \ "children") (0).toString)
      logger.info(((json \ "children") (1) \ "name").toString)
      logger.info((json \\ classOf[JInt]).toString)
      logger.info((json \ "children" \\ classOf[JString]).toString)
    }
  }
}
