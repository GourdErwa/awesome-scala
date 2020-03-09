package com.gourd.scala.base.generic_classes

import org.slf4j.LoggerFactory

/** 协变/逆变/不变
  * 型变是复杂类型的子类型关系与其组件类型的子类型关系的相关性。
  * Scala支持 泛型类的类型参数的型变注释，允许它们是协变的，逆变的，或在没有使用注释的情况下是不变的。
  * 在类型系统中使用型变允许我们在复杂类型之间建立直观的连接，而缺乏型变则会限制类抽象的重用性。
  *
  * 协变，逆变理解示例 [[Function1]]
  *
  * 使用场景（遵循 PECS 原则）
  * - 数据源作为只读时，使用协变
  * - 数据源作为只写时，使用逆变
  * - 数据源作为读写时，使用不变
  *
  * 以下是如何确定您的类型是否 ParametricType[T] 可以/不能 协变/逆变：
  * - 当一个类型不调用泛型作为参数的类型的方法时，它可以是协变的。反之。
  * 原型的例子：Seq[+A]，Option[+A]，Future[+T]
  * - 如果需要返回泛型时，则它不能是逆变的。
  * 原型的例子：Function1[-T1, +R]，CanBuildFrom[-From, -Elem, +To]，OutputChannel[-Msg]
  * @author Li.Wei by 2019-08-07
  */
object VariancesApp {
  val logger = LoggerFactory.getLogger("VariancesApp")

  def main(args: Array[String]): Unit = {}

  abstract class Animal {
    def name: String
  }

  case class Cat(name: String) extends Animal

  case class Dog(name: String) extends Animal

  /**
    * 协变
    * 使用注释 +A，可以使一个泛型类的类型参数 A 成为协变。
    * 对于某些类class List[+A]，使A成为协变意味着对于两种类型A和B，如果A是B的子类型，那么List[A]就是List[B]的子类型。
    */
  {
    def printAnimalNames(animals: List[Animal]): Unit = {
      animals.foreach { animal =>
        println(animal.name)
      }
    }

    // Scala 标准库有一个通用的不可变的类sealed abstract class List[+A]，其中类型参数A是协变的。
    // printAnimalNames接收List[Animal]类型的参数
    // 因为List的参数是支持协变的，并且Cat和Dog都是Animal的子类
    // 所以，List[Cat]和List[Dog]都可以作为参数传入printAnimalNames方法
    val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
    val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))
    printAnimalNames(cats)
    // Whiskers
    // Tom
    printAnimalNames(dogs)
    // Fido
    // Rex
  }

  /**
    * 逆变
    * 通过使用注释 -A，可以使一个泛型类的类型参数 A 成为逆变。
    * 与协变完全相反，对于某个类class Writer[-A]，使A逆变意味着对于两种类型A和B，如果A是B的子类型，那么Writer[B]是Writer[A]的子类型。
    */
  {
    abstract class Printer[-A] {
      def print(value: A): Unit
    }
    // AnimalPrinter继承了Printer，Printer泛型支持逆变
    // 由于Cat是Animal的子类型，所以Printer[Animal]是Printer[Cat]的子类型
    // 从逻辑上因为Printer[Animal]可以输出所有动物的名称，因此也可以输出Cat，这是合理的。
    // 但是Printer[Cat]只能输出Cat，并不可以输出其他的Animal，所以Printer[Animal]可以替换Printer[Cat]
    class AnimalPrinter extends Printer[Animal] {
      def print(animal: Animal): Unit =
        println("The animal's name is: " + animal.name)
    }

    class CatPrinter extends Printer[Cat] {
      def print(cat: Cat): Unit =
        println("The cat's name is: " + cat.name)
    }

    val myCat: Cat = Cat("Boots")

    // Printer支持逆变，因此该方法可以接收Printer[Animal]类型的参数。
    def printMyCat(printer: Printer[Cat]): Unit = {
      printer.print(myCat)
    }

    val catPrinter: Printer[Cat] = new CatPrinter
    val animalPrinter: Printer[Animal] = new AnimalPrinter

    printMyCat(catPrinter) // The cat's name is: Boots
    printMyCat(animalPrinter) // The animal's name is: Boots
  }

  /**
    * 不变
    * 默认情况下，Scala中的泛型类是不变的。这意味着它们既不是协变的也不是逆变的。
    */
  {
    // Container是不变的。 Container[Cat]不是Container[Animal]的子类型
    class Container[A](value: A) {
      private var _value: A = value

      def getValue: A = _value

      def setValue(value: A): Unit = {
        _value = value
      }
    }
    // Container不是协变的，是不变的，这点很重要，否则会将狗的值分配给猫
    val catContainer: Container[Cat] = new Container(Cat("Felix"))
    val animalContainer: Container[Animal] = catContainer // 编译报错
    animalContainer.setValue(Dog("Spot"))
    val cat: Cat = catContainer.getValue
  }
}
