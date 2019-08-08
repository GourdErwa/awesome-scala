package com.gourd.scala

/**
  * Scala 环境下至少存在 6种 Json 解析的类库，这里面不包括 Java 语言实现的 Json 类库。
  * 所有这些库都有一个非常相似的抽象语法树(AST)。而 json4s 项目旨在提供一个单一的 AST 树供其他 Scala 类库来使用
  *
  * json4s 库操作示例
  * [[org.json4s.JsonAST]] Json AST 结构定义 ， 重点理解结构转换图 https://raw.github.com/json4s/json4s/3.3/core/json.png
  *
  * {{{
  * sealed abstract class JValue
  * case object JNothing extends JValue // 'zero' for JValue
  * case object JNull extends JValue
  * case class JString(s: String) extends JValue
  * case class JDouble(num: Double) extends JValue
  * case class JDecimal(num: BigDecimal) extends JValue
  * case class JInt(num: BigInt) extends JValue
  * case class JBool(value: Boolean) extends JValue
  * case class JObject(obj: List[JField]) extends JValue
  * case class JArray(arr: List[JValue]) extends JValue
  *
  * type JField = (String, JValue)
  * }}}
  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * native  lift-json
  * jackson jackson解析到AST的实现
  * {{{
  * import  org.json4s.native.JsonMethods._
  * import  org.json4s.jackson.JsonMethods._
  * }}}
  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * DoubleMode 将浮点型数据转换成JDouble，这种方式下DSL使用：
  * {{{
  *   import org.json4s.JsonDSL._
  *   // or
  *   import org.json4s.JsonDSL.WithDouble._
  * }}}
  *
  * BigDecimalMode 将浮点型数据转换成JDecimal，这种方式下DSL使用：
  * {{{
  *    import org.json4s.JsonDSL.WithBigDecimal._
  * }}}
  * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * 演示示例
  * [[com.gourd.scala.json4s.Json4sParsing]]        JSON=> JValue
  * [[com.gourd.scala.json4s.Json4sProducing]]      构建 JValue ，使用 DSL 规则
  * [[com.gourd.scala.json4s.Json4sSerialization]]  JSON=>JValue,JValue=>case class
  * [[com.gourd.scala.json4s.Json4sJsonOptions]]    JSON 相关操作：合并、查询
  *
  * @author Li.Wei by 2019-08-08
  */
package object json4s {

}
