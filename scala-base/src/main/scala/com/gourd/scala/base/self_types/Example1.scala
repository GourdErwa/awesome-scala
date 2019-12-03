package com.gourd.scala.base.self_types

import com.gourd.scala.MainApp

/**
  * 自类型
  * 用于声明一个特质必须混入其他特质，尽管该特质没有直接扩展其他特质。 这使得所依赖的成员可以在没有导入的情况下使用。
  * 自类型是一种细化 this 或 this 别名之类型的方法。 语法看起来像普通函数语法，但是意义完全不一样。
  * 要在特质中使用自类型，写一个标识符，跟上要混入的另一个特质，以及 =>（例如 someIdentifier: SomeOtherTrait =>）。
  *
  * @author Li.Wei by 2019-08-06
  */
// 用在有内部类的情况
class Outer {
  out =>
  val v1 = "spark"

  class Inner {
    println(out.v1) // 用outer表示外部类，相当于Outer.this
  }

}

trait User {
  def username: String
}

trait Tweeter {
  self: User => // 重新赋予 this 的类型
  def tweet(tweetText: String): Unit = println(s"$username: $tweetText")
}

class VerifiedTweeter(val username_ : String) extends Tweeter with User { // 我们混入特质 User 因为 Tweeter 需要
  override def username = s"real $username_"
}


object Example1 extends MainApp {

  val realBeyoncé = new VerifiedTweeter("Beyoncé")
  realBeyoncé.tweet("Just spilled my glass of lemonade") // 打印出 "real Beyoncé: Just spilled my glass of lemonade"

}
