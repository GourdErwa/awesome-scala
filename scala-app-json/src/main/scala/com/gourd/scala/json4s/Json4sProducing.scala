package com.gourd.scala
package json4s

import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.slf4j.LoggerFactory

/** Producing JSON
  * JValue => json str
  *
  * @author Li.Wei by 2019-08-08
  */
object Json4sProducing {

  private val logger = LoggerFactory.getLogger("Json4sProducing")

  def main(args: Array[String]): Unit = {
    ///////////////////////////////////////////////////////////////////////////////////
    // 默认 DSL 规则
    ///////////////////////////////////////////////////////////////////////////////////
    {
      /*
      基本数据类型映射到JSON的基本数据类型
      任何Seq类型映射到JSON的数组类型
       */
      logger.info(compact(render(List(1, 2, 3)))) // [1,2,3]

      // Tuple2[String, Any]将生成字段
      logger.info(compact(render("name" -> "joe"))) // {"name":"joe"}

      // 操作符 ~ 通过组合字段生成对象。
      logger.info(compact(render(("name" -> "joe") ~ ("age" -> 35)))) // {"name":"joe","age":35}

      // 所有取值都是可选的，如果值不存在，则直接剔除掉键值对
      logger.info(compact(render(("name" -> "joe") ~ ("age" -> Some(35))))) // {"name":"joe","age":35}
      logger.info(compact(render(("name" -> "joe") ~ ("age" -> (None: Option[Int]))))) // {"name":"joe"}
    }

    ///////////////////////////////////////////////////////////////////////////////////
    // 自定义DSL规则
    // 必须实现 type DslConversion = T => JValue 转换
    ///////////////////////////////////////////////////////////////////////////////////
    {
      case class Winner(id: Long, numbers: List[Int])

      case class Lotto(id: Long, winningNumbers: List[Int], winners: List[Winner], drawDate: Option[java.util.Date])

      val winners = List(Winner(23, List(2, 45, 34, 23, 3, 5)), Winner(54, List(52, 3, 12, 11, 18, 22)))
      val lotto = Lotto(5, List(2, 45, 34, 23, 7, 5, 3), winners, None)

      val json =
        ("lotto" ->
          ("lotto-id" -> lotto.id) ~
            ("winning-numbers" -> lotto.winningNumbers) ~
            ("draw-date" -> lotto.drawDate.map(_.toString)) ~
            ("winners" ->
              lotto.winners.map { w =>
                (("winner-id" -> w.id) ~
                  ("numbers" -> w.numbers))
              }))
      logger.info(compact(render(json)))
      /*
       {"lotto":{"lotto-id":5,"winning-numbers":[2,45,34,23,7,5,3],
       "winners":[{"winner-id":23,"numbers":[2,45,34,23,3,5]},{"winner-id":54,"numbers":[52,3,12,11,18,22]}]}}
       */
    }
  }
}
