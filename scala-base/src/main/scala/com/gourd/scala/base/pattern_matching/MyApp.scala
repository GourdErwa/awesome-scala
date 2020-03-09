package com.gourd.scala.base.pattern_matching

import org.slf4j.LoggerFactory

/**
  * 模式匹配是检查某个值（value）是否匹配某一个模式的机制，一个成功的匹配同时会将匹配值解构为其组成部分。
  * 它是Java中的switch语句的升级版，同样可以用于替代一系列的 if/else 语句。
  *
  * 匹配类型
  * * 仅匹配类型
  * * 案例类（case classes）的匹配
  * * 模式守卫（Pattern gaurds）
  * * 提取器对象（extractor objects）中的unapply方法来定义非案例类对象的匹配
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 语法
    * 一个模式匹配语句包括一个待匹配的值，match关键字，以及至少一个case语句。
    */
  {
    import scala.util.Random

    val x: Int = Random.nextInt(10)

    x match {
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case _ => "other" // 表示匹配其余所有情况，在这里就是其他可能的整型值。
    }

    // match表达式是有返回值的，因为所有的情况均返回string，所以matchTest函数的返回值类型是string
    def matchTest(x: Int): String = x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "other"
    }
  }

  /**
    * 案例类的模式匹配
    * 案例类非常适合用于模式匹配。
    * showNotification函数接受一个抽象类Notification对象作为输入参数，然后匹配其具体类型。
    * 也就是判断它是一个Email，SMS，还是VoiceRecording。
    * 在case Email(sender, title, _)中，对象的sender和title属性在返回值中被使用，而body属性则被忽略，故使用_代替。
    */
  object pattern_match {

    abstract class Notification

    case class Email(sender: String, title: String, body: String) extends Notification

    case class SMS(caller: String, message: String) extends Notification

    case class VoiceRecording(contactName: String, link: String) extends Notification

    def showNotification(notification: Notification): String = {
      notification match {
        case Email(sender, title, _) =>
          s"You got an email from $sender with title: $title"
        case SMS(number, message) =>
          s"You got an SMS from $number! Message: $message"
        case VoiceRecording(name, link) =>
          s"you received a Voice Recording from $name! Click the link to hear it: $link"
      }
    }

    val someSms: SMS = SMS("12345", "Are you there?")
    val someVoiceRecording: VoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")

    // prints You got an SMS from 12345! Message: Are you there?
    println(showNotification(someSms))

    // you received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
    println(showNotification(someVoiceRecording))
  }

  /**
    * 模式守卫
    * 为了让匹配更加具体，可以使用模式守卫，也就是在模式后面加上if <boolean expression>。
    * 在case Email(sender, _, _) if importantPeopleInfo.contains(sender)中。
    * 除了要求notification是Email类型外，还需要sender在重要人物列表importantPeopleInfo中，才会匹配到该模式。
    */
  {
    import com.gourd.scala.base.pattern_matching.MyApp.pattern_match._
    def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
      notification match {
        case Email(sender, _, _) if importantPeopleInfo.contains(sender) =>
          "You got an email from special someone!"
        case SMS(number, _) if importantPeopleInfo.contains(number) =>
          "You got an SMS from special someone!"
        case other =>
          // nothing special, delegate to our original showNotification function
          showNotification(other)
      }
    }

    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

    val importantSms = SMS("867-5309", "Are you there?")
    val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
    val someSms = SMS("867-5111", "I'm here! Where are you?")
    // You got an SMS from special someone!
    println(showImportantNotification(importantSms, importantPeopleInfo))
    // you received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
    println(showImportantNotification(someVoiceRecording, importantPeopleInfo))
    // You got an SMS from 867-5111! Message: I'm here! Where are you?
    println(showImportantNotification(someSms, importantPeopleInfo))
  }

  /**
    * 仅匹配类型
    * 当不同类型对象需要调用不同方法时，仅匹配类型的模式非常有用。
    * 一般使用类型的首字母作为case的标识符。
    */
  {
    abstract class Device
    case class Phone(model: String) extends Device {
      def screenOff = "Turning screen off"
    }
    case class Computer(model: String) extends Device {
      def screenSaverOn = "Turning screen saver on..."
    }
    // goIdle函数对不同类型的Device有着不同的表现，采用类型的首字母作为case的标识符：p、c
    def goIdle(device: Device) = device match {
      case p: Phone => p.screenOff
      case c: Computer => c.screenSaverOn
    }
  }

  /**
    * 密封类
    * 特质（trait）和类（class）可以用sealed标记为密封的，这意味着其所有子类都必须与之定义在相同文件中，从而保证所有子类型都是已知的。
    * 用于模式匹配，当抽象类加了sealed关键字之后，scala在编译的时候会进行检查，如果有漏掉的case类型，会警告提示。
    */
  {
    // Couch和Chair必须和Furniture定义在相同的文件中
    sealed abstract class Furniture
    case class Couch() extends Furniture
    case class Chair() extends Furniture

    // 由于Man加了sealed关键字，模式匹配时，scala会检测是否遗漏匹配的类型，编译时会警告提醒
    sealed abstract class Man
    case object American extends Man
    case object Chinese extends Man
    case object Russia extends Man

    // Warning:(61, 29) match may not be exhaustive.It would fail on the following input: Russia
    // def from(m: Man) = m match {
    def from(m: Man): Unit = m match {
      case American ⇒ println("American")
      case Chinese ⇒ println("Chinese")
    }

    // 如果确定不会有Russia类型的对象传入，可以使用下面的方法去掉警告提示
    def from2(m: Man): Unit = (m: @unchecked) match {
      case American ⇒ println("American")
      case Chinese ⇒ println("Chinese")
    }
  }
}