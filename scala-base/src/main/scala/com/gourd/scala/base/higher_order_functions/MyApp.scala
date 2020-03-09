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
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 使用
    * doubleSalary是一个函数，被应用在了salaries的每一个元素上
    */
  {
    val salaries = Seq(20000, 70000, 40000)
    val doubleSalary = (x: Int) => x * 2
    val newSalaries1 = salaries.map(doubleSalary) // List(40000, 140000, 80000)
    val newSalaries2 = salaries.map(x => x * 2) // 没有显示的声明参数x的类型，scala可以自行推断出类型
    val newSalaries3 = salaries.map(_ * 2) // 省略参数名，使用_ 替代
  }

  /**
    * 强制转换方法为函数
    * 传入一个对象方法作为高阶函数的参数,Scala编译器会将方法强制转换为一个函数。
    */
  {
    // 编译器强制将方法convertCtoF转成了函数x => convertCtoF(x)
    // (注: x是编译器生成的变量名，保证在其作用域是唯一的)
    case class WeeklyWeatherForecast(temperatures: Seq[Double]) {

      private def convertCtoF(temp: Double) = temp * 1.8 + 32

      // <-- passing the method convertCtoF
      def forecastInFahrenheit: Seq[Double] = temperatures.map(convertCtoF)
    }
  }

  /**
    * 接收函数作为参数的函数
    * 使用高阶函数的一个原因是减少冗余的代码。
    *
    */
  {
    // 未使用高阶函数，给员工计算薪资，每一种计算方式定义一个方法
    object SalaryRaiser {

      def smallPromotion(salaries: List[Double]): List[Double] =
        salaries.map(salary => salary * 1.1)

      def greatPromotion(salaries: List[Double]): List[Double] =
        salaries.map(salary => salary * math.log(salary))

      def hugePromotion(salaries: List[Double]): List[Double] =
        salaries.map(salary => salary * salary)
    }

    // 使用高阶函数，将薪资列表和计算薪资的函数作为参数，传入计算薪资的方法中
    object SalaryRaiser2 {

      private def promotion(salaries: List[Double],
                            promotionFunction: Double => Double): List[Double] =
        salaries.map(promotionFunction)

      def smallPromotion(salaries: List[Double]): List[Double] =
        promotion(salaries, salary => salary * 1.1)

      def bigPromotion(salaries: List[Double]): List[Double] =
        promotion(salaries, salary => salary * math.log(salary))

      def hugePromotion(salaries: List[Double]): List[Double] =
        promotion(salaries, salary => salary * salary)
    }
  }

  /**
    * 返回值为函数的函数
    * urlBuilder的返回类型是(String, String) => String，这意味着返回的匿名函数有两个String参数，返回一个String
    */
  {
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

  /**
    * 嵌套方法
    * 在Scala中可以嵌套定义方法。例如以下对象提供了一个factorial方法来计算给定数值的阶乘。
    */
  {
    object NestedFun {
      def factorial(x: Int): Int = {
        @scala.annotation.tailrec
        def fact(x: Int, accumulator: Int): Int = {
          if (x <= 1) accumulator
          else fact(x - 1, x * accumulator)
        }

        fact(x, 1)
      }

      println("Factorial of 2: " + factorial(2)) // Factorial of 2: 2
      println("Factorial of 3: " + factorial(3)) // Factorial of 3: 6
    }
  }
}
