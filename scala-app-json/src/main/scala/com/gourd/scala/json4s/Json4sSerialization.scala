package com.gourd.scala.json4s

import java.util.Date

import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization.{read, write}
import org.slf4j.LoggerFactory


/** Json4sSerialization JSON
  *
  * @author Li.Wei by 2019-08-08
  */
object Json4sSerialization {

  private val logger = LoggerFactory.getLogger("Json4sSerialization")

  def main(args: Array[String]): Unit = {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // Json4sSerialization 使用 DefaultFormats
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    {
      case class GameJobSetting
      (startDt: Date = new Date(), // 默认为昨天 dt
       endDt: Date = new Date(), // 默认为今天 dt
       calculatePriority: Int = -1, // 处理优先级 ， -1 表示忽略
       includeAppIds: Array[String] = Array(),
       excludeAppIds: Array[String] = Array(),
       includeTasks: Array[String] = Array(),
       excludeTasks: Array[String] = Array(),
       otherParameters: Map[String, String] = Map(),
       elasticSetting: ElasticSetting = ElasticSetting()
      ) {}
      case class ElasticSetting(delHistoricalData: Boolean = false)

      implicit val formats: DefaultFormats.type = DefaultFormats
      // case 对象 -> json str
      val json = write(GameJobSetting())
      logger.info(json)

      // json str -> case 对象
      val gameJobSetting = read[GameJobSetting](json)
      logger.info(gameJobSetting.toString)

      // json str -> JValue -> case 对象
      import org.json4s._
      import org.json4s.jackson.JsonMethods._
      val gameJobSetting1 = parse(json).extract[GameJobSetting]
      logger.info(gameJobSetting1.toString)
    }
  }
}
