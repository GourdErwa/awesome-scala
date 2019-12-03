package com.gourd.scala.base.pattern_matching

import com.gourd.scala.MainApp

/**
  * 模式匹配是检查某个值（value）是否匹配某一个模式的机制，一个成功的匹配同时会将匹配值解构为其组成部分。
  * 它是Java中的switch语句的升级版，同样可以用于替代一系列的 if/else 语句。
  *
  * 匹配类型
  * * 仅匹配类型
  * * 案例类（case classes）的匹配
  * * 模式守卫（Pattern gaurds）
  * * 提取器对象（extractor objects）中的unapply方法来定义非案例类对象的匹配
  *
  * @author Li.Wei by 2019-08-06
  */
object Example1 extends MainApp {

  ///////////////////////////////////////////////////////////////////////////////////
  // 语法： 一个模式匹配语句包括一个待匹配的值，match关键字，以及至少一个case语句。
  ///////////////////////////////////////////////////////////////////////////////////
  {
    import scala.util.Random
    val x: Int = Random.nextInt(10)
    x match {
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case _ => "other" // case _表示匹配其余所有情况
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // match表达式具有一个结果值
  ///////////////////////////////////////////////////////////////////////////////////
  {
    def matchTest(x: Int): String = x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "other"
    }

    matchTest(3) // other
    matchTest(1) // one
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // 仅匹配类型
  ///////////////////////////////////////////////////////////////////////////////////
  {
    abstract class Device
    case class Phone(model: String) extends Device {
      def screenOff = "Turning screen off"
    }
    case class Computer(model: String) extends Device {
      def screenSaverOn = "Turning screen saver on..."
    }

    def goIdle(device: Device) = device match {
      case p: Phone => p.screenOff
      case c: Computer => c.screenSaverOn
    }
  }

  {
    abstract class Notification

    case class Email(sender: String, title: String, body: String) extends Notification

    case class SMS(caller: String, message: String) extends Notification

    case class VoiceRecording(contactName: String, link: String) extends Notification {}

    // 案例类（case classes）的匹配
    /*
     Notification 是一个虚基类，它有三个具体的子类Email, SMS和VoiceRecording，我们可以在这些案例类(Case Class)上像这样使用模式匹配：
     showNotification函数接受一个抽象类Notification对象作为输入参数，然后匹配其具体类型。
    （也就是判断它是一个Email，SMS，还是VoiceRecording）。
     在case Email(sender, title, _)中，对象的sender和title属性在返回值中被使用，而body属性则被忽略，故使用_代替。
     */
    def showNotification(notification: Notification): String = {
      notification match {
        case Email(sender, title, _) => s"You got an email from $sender with title: $title"
        case SMS(number, message) => s"You got an SMS from $number! Message: $message"
        case VoiceRecording(name, link) =>
          s"you received a Voice Recording from $name! Click the link to hear it: $link"
      }
    }

    val someSms = SMS("12345", "Are you there?")
    val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
    // prints You got an SMS from 12345! Message: Are you there?
    println(showNotification(someSms))
    // you received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123
    println(showNotification(someVoiceRecording))

    // 模式守卫（Pattern gaurds）
    /*
    为了让匹配更加具体，可以使用模式守卫，也就是在模式后面加上if <boolean expression>。
    在case Email(sender, _, _) if importantPeopleInfo.contains(sender)中，除了要求notification是Email类型外，
    还需要sender在重要人物列表importantPeopleInfo中，才会匹配到该模式。
     */
    def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
      notification match {
        case Email(sender, _, _) if importantPeopleInfo.contains(sender) => "You got an email from special someone!"
        case SMS(number, _) if importantPeopleInfo.contains(number) => "You got an SMS from special someone!"
        case other =>
          showNotification(other) // nothing special, delegate to our original showNotification function
      }
    }

    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")
    val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
    val importantSms = SMS("867-5309", "I'm here! Where are you?")

    println(showImportantNotification(SMS("867-5309", "Are you there?"), importantPeopleInfo))
    println(showImportantNotification(VoiceRecording("Tom", "voicerecording.org/id/123"), importantPeopleInfo))
    println(showImportantNotification(importantEmail, importantPeopleInfo))
    println(showImportantNotification(importantSms, importantPeopleInfo))
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // 提取器对象（extractor objects）中的unapply方法来定义非案例类对象的匹配
  ///////////////////////////////////////////////////////////////////////////////////
  {
    import scala.util.Random

    object CustomerID {

      def apply(name: String) = s"$name--${Random.nextLong}"

      def unapply(customerID: String): Option[String] = {
        val stringArray: Array[String] = customerID.split("--")
        if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
      }
    }

    val customer1ID = CustomerID("Sukyoung") // Sukyoung--23098234908
    customer1ID match {
      case CustomerID(name) => println(name) // prints Sukyoung
      case _ => println("Could not extract a CustomerID")
    }
  }

}