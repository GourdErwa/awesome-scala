package com.gourd.scala
package json4s

import java.text.SimpleDateFormat
import java.util.Date

import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read, write}
import org.json4s.{DefaultFormats, _}
import org.slf4j.LoggerFactory

/** Json4sSerialization JSON
  *
  * 序列化工具类
  * [[org.json4s.jackson.Serialization]] 示例 https://www.programcreek.com/scala/org.json4s.jackson.Serialization
  * [[org.json4s.Serialization]]         示例 https://www.programcreek.com/scala/org.json4s.native.Serialization
  *
  * 序列化支持：
  * - 任意深度的样式类
  * - 所有的基本类型，包括BigInt与Symbol
  * - List，Seq，Array，Set以及Map
  * - scala.Option
  * - java.util.Date
  * - Polymorphic Lists
  * - 递归类型
  * - 只含可序列化字段的类
  * - 自定义序列化函数
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
      logger.info(
        s"""case class GameJobSetting-> json str=
           |$json""".stripMargin)

      // json str -> case 对象
      val gameJobSetting = read[GameJobSetting](json)
      logger.info(
        s"""json str -> case class GameJobSetting=
           |$gameJobSetting""".stripMargin)

      // json str -> JValue -> case 对象
      val gameJobSetting1 = parse(json).extract[GameJobSetting]
      logger.info(
        s"""json str -> JValue -> case class GameJobSetting=
           |$gameJobSetting1""".stripMargin)
    }


    {
      // 如果样式类具有辅助构造函数，提取函数会尽量匹配最佳的构造函数
      case class Bike(make: String, price: Int) {
        def this(price: Int) = this("Trek", price)
      }

      // 日期格式可以通过重写DefaultFormats方法实现
      implicit val formats: DefaultFormats = new DefaultFormats {
        override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
      }
      logger.info(
        s"""parse({"price":350}).extract[Bike]=
           |${parse(""" {"price":350} """).extract[Bike]}""".stripMargin) // Bike(Trek,350)
    }

    {
      // JSON对象也可以转换成Map[String, _]，每个字段将转换成键值对
      val json = parse(
        """
         {
           "name": "joe",
           "addresses": {
             "address1": {
               "street": "Bulevard",
               "city": "Helsinki"
             },
             "address2": {
               "street": "Soho",
               "city": "London"
             }
           }
         }""")
      case class Address(street: String, city: String)
      case class PersonWithAddresses(name: String, addresses: Map[String, Address])
      implicit val formats: DefaultFormats.type = DefaultFormats

      logger.info(
        s"""json.extract[PersonWithAddresses]=
           |${json.extract[PersonWithAddresses]}""".stripMargin)
      // PersonWithAddresses(joe,Map(address1 -> Address(Bulevard,Helsinki), address2 -> Address(Soho,London)))
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  // Json4sSerialization 自定义 CustomSerializer
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  {
    // java.sql.Date 不支持基本类型，需要自定义序列化
    case class Person(name: String, age: Int, birthday: java.sql.Date)

    case object DateSerializer extends CustomSerializer[Date](
      _ => ( {
        case JString(s) => java.sql.Date.valueOf(s)
        case JNull => null
      }, {
        case d: Date => JString(d.toString)
      })
    )
  }

}
