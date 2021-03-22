package com.gourd.scala.base.generic_classes

import com.gourd.scala.MainApp

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
  *
  * @author Li.Wei by 2019-08-07
  */
object VariancesApp extends MainApp

///////////////////////////////////////////////////////////////////////////////////
// 协变-covariant
///////////////////////////////////////////////////////////////////////////////////
/*
使用注释 +A，可以使一个泛型类的类型参数 A 成为协变。
对于某些类 class List[+A]，使 A 成为协变意味着对于两种类型 A 和 B，
如果 A 是 B 的子类型，那么 List[A] 就是 List[B] 的子类型。
这允许我们使用泛型来创建非常有用和直观的子类型关系。
 */
class Foo[+A] // 定义示例

abstract class Animal {
  def name: String
}

case class Cat(name: String) extends Animal

case class Dog(name: String) extends Animal

object CovarianceTest extends MainApp {
  val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
  val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      logger.info(animal.name)
    }
  }

  printAnimalNames(cats) // 协变
  // Whiskers
  // Tom

  printAnimalNames(dogs) // 协变
  // Fido
  // Rex
}

///////////////////////////////////////////////////////////////////////////////////
// 逆变-contravariant
///////////////////////////////////////////////////////////////////////////////////
/*
通过使用注释 -A，可以使一个泛型类的类型参数 A 成为逆变。
与协变类似，这会在类及其类型参数之间创建一个子类型关系，但其作用与协变完全相反。
也就是说，对于某个类 class Writer[-A] ，使 A 逆变意味着对于两种类型 A 和 B，
如果 A 是 B 的子类型，那么 Writer[B] 是 Writer[A] 的子类型。
 */
class Bar[-A] // 定义示例

abstract class Printer[-A] {
  def print(value: A): Unit
}

class AnimalPrinter extends Printer[Animal] {
  def print(animal: Animal): Unit =
    println("The animal's name is: " + animal.name)
}

class CatPrinter extends Printer[Cat] {
  def print(cat: Cat): Unit =
    println("The cat's name is: " + cat.name)
}

object ContravariantTest extends App {

  {
    // 示例 1
    val myCat: Cat = Cat("Boots")

    def printMyCat(printer: Printer[Cat]): Unit = {
      printer.print(myCat)
    }

    val catPrinter: Printer[Cat] = new CatPrinter
    val animalPrinter: Printer[Animal] = new AnimalPrinter

    printMyCat(catPrinter)
    printMyCat(animalPrinter)
  }

  {
    // 示例 2 - scala.Function1
    val f1: Int => String = x => s"Int($x)"
    val f2: Any => String = x => s"Any($x)"
    // 凡是f1可以使用的地方，f2都是可以用的；但反过来不行。
    //所以类型Function1[Any, String]应该是Function1[Int, String]的子类。
  }

}

///////////////////////////////////////////////////////////////////////////////////
// 不变
///////////////////////////////////////////////////////////////////////////////////
/*
默认情况下，Scala中的泛型类是不变的。 这意味着它们既不是协变的也不是逆变的
 */
class Baz[A] // 定义示例
