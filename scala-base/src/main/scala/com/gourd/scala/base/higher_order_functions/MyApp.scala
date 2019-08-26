package com.gourd.scala.base.higher_order_functions

import org.slf4j.LoggerFactory

/**
  * 高阶函数是指使用其他函数作为参数、或者返回一个函数作为结果的函数。
  *
  * 在数学和计算机科学中，高阶函数是至少满足下列一个条件的函数:
  * - 接受一个或多个函数作为输入
  * - 输出一个函数
  *
  * 在Scala中函数是“一等公民”，所以允许定义高阶函数。
  * 这里的术语可能有点让人困惑，我们约定，使用函数值作为参数，或者返回值为函数值的“函数”和“方法”，均称之为“高阶函数”。
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  val salaries = Seq(20000, 70000, 40000)
  val doubleSalary = (x: Int) => x * 2
  val newSalaries = salaries.map(doubleSalary) // List(40000, 140000, 80000)
  // val newSalaries = salaries.map(x => x * 2) // List(40000, 140000, 80000)
  // val newSalaries = salaries.map(_ * 2)

  def main(args: Array[String]): Unit = {
  }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 强制转换方法为函数
///////////////////////////////////////////////////////////////////////////////////////////////////////
case class WeeklyWeatherForecast(temperatures: Seq[Double]) {

  private def convertCtoF(temp: Double): Double = temp * 1.8 + 32

  // 方法convertCtoF被传入forecastInFahrenheit。这是可以的，因为编译器强制将方法convertCtoF转成了函数x => convertCtoF(x)
  // 注: x是编译器生成的变量名，保证在其作用域是唯一的
  def forecastInFahrenheit: Seq[Double] = temperatures.map(convertCtoF) // <-- passing the method convertCtoF
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 接收函数作为参数的函数
///////////////////////////////////////////////////////////////////////////////////////////////////////
object SalaryRaiser {

  object Original {

    def smallPromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * 1.1)

    def greatPromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * math.log(salary))

    def hugePromotion(salaries: List[Double]): List[Double] =
      salaries.map(salary => salary * salary)
  }

  // 注意这三个方法的差异仅仅是提升的比例不同，为了简化代码，其实可以把重复的代码提到一个高阶函数中
  object Simplify {
    private def promotion(salaries: List[Double], promotionFunction: Double => Double): List[Double] =
      salaries.map(promotionFunction)

    def smallPromotion(salaries: List[Double]): List[Double] =
      promotion(salaries, salary => salary * 1.1)

    def bigPromotion(salaries: List[Double]): List[Double] =
      promotion(salaries, salary => salary * math.log(salary))

    def hugePromotion(salaries: List[Double]): List[Double] =
      promotion(salaries, salary => salary * salary)
  }

}

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 返回函数的函数
// 注意urlBuilder的返回类型是(String, String) => String，这意味着返回的匿名函数有两个String参数，返回一个String。
// 在这个例子中，返回的匿名函数是(endpoint: String, query: String) => s"https://www.example.com/$endpoint?$query"。
///////////////////////////////////////////////////////////////////////////////////////////////////////
object ReturnFun {
  def urlBuilder(ssl: Boolean, domainName: String): (String, String) => String = {
    val schema = if (ssl) "https://" else "http://"
    (endpoint: String, query: String) => s"$schema$domainName/$endpoint?$query"
  }

  val domainName = "www.example.com"

  def getURL = urlBuilder(ssl = true, domainName)

  val endpoint = "users"
  val query = "id=1"
  val url = getURL(endpoint, query) // "https://www.example.com/users?id=1": String
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
// 嵌套方法
// 在Scala中可以嵌套定义方法。例如以下对象提供了一个factorial方法来计算给定数值的阶乘：
///////////////////////////////////////////////////////////////////////////////////////////////////////
object NestedFun {
  def factorial(x: Int): Int = {
    @scala.annotation.tailrec
    def fact(x: Int, accumulator: Int): Int = {
      if (x <= 1) accumulator
      else fact(x - 1, x * accumulator)
    }

    fact(x, 1)
  }

  println("Factorial of 2: " + factorial(2))
  println("Factorial of 3: " + factorial(3))
  // Factorial of 2: 2
  // Factorial of 3: 6
}