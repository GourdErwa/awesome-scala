package com.gourd.scala.overviews

/**
  * Scala 集合类系统地区分了可变的和不可变的集合。
  * 可变集合可以在适当的地方被更新或扩展。这意味着你可以修改，添加，移除一个集合的元素。而不可变集合类，相比之下，永远不会改变。
  * 不过，你仍然可以模拟添加，移除或更新操作。但是这些操作将在每一种情况下都返回一个新的集合，同时使原来的集合不发生改变。
  *
  * 所有的集合类都可以在包scala.collection 或scala.collection.mutable，scala.collection.immutable，
  * scala.collection.generic中找到。客户端代码需要的大部分集合类都独立地存在于3种变体中，
  * 它们位于scala.collection, scala.collection.immutable, scala.collection.mutable包。
  * 每一种变体在可变性方面都有不同的特征。
  *
  * scala.collection.immutable包是的集合类确保不被任何对象改变。例如一个集合创建之后将不会改变。
  * 因此，你可以相信一个事实，在不同的点访问同一个集合的值，你将总是得到相同的元素。。
  *
  * scala.collection.mutable包的集合类则有一些操作可以修改集合。所以处理可变集合意味着你需要去理解哪些代码的修改会导致集合同时改变。
  *
  * scala.collection包中的集合，既可以是可变的，也可以是不可变的。例如: collection.IndexedSeq[T]
  * 就是 collection.immutable.IndexedSeq[T] 和collection.mutable.IndexedSeq[T]这两类的超类。
  * scala.collection包中的根集合类中定义了相同的接口作为不可变集合类，
  * 同时，scala.collection.mutable包中的可变集合类代表性的添加了一些有辅助作用的修改操作到这个immutable 接口。
  *
  * 根集合类与不可变集合类之间的区别是不可变集合类的客户端可以确保没有人可以修改集合。
  * 然而，根集合类的客户端仅保证不修改集合本身。即使这个集合类没有提供修改集合的静态操作，它仍然可能在运行时作为可变集合被其它客户端所修改。
  *
  * 默认情况下，Scala 一直采用不可变集合类。例如，如果你仅写了Set 而没有任何加前缀也没有从其它地方导入Set，你会得到一个不可变的set，
  * 另外如果你写迭代，你也会得到一个不可变的迭代集合类，这是由于这些类在从scala中导入的时候都是默认绑定的。
  * 为了得到可变的默认版本，你需要显式的声明collection.mutable.Set或collection.mutable.Iterable.
  *
  * 一个有用的约定，如果你想要同时使用可变和不可变集合类，只导入collection.mutable包即可。
  * {{{
  * import scala.collection.mutable  //导入包scala.collection.mutable
  * }}}
  * 然而，像没有前缀的Set这样的关键字， 仍然指的是一个不可变集合，然而mutable.Set指的是可变的副本（可变集合）。
  *
  * 集合树的最后一个包是collection.generic。这个包包含了集合的构建块。
  * 集合类延迟了collection.generic类中的部分操作实现，另一方面集合框架的用户需要引用collection.generic中类在异常情况中。
  *
  * 为了方便和向后兼容性，一些导入类型在包scala中有别名，所以你能通过简单的名字使用它们而不需要import。
  * 这有一个例子是List 类型，它可以用以下两种方法使用，如下：
  * {{{
  * scala.collection.immutable.List // 这是它的定义位置
  * scala.List //通过scala 包中的别名
  * List // 因为scala._ 总是是被自动导入。
  * }}}
  *
  * 其它类型的别名有： Traversable, Iterable, Seq, IndexedSeq, Iterator, Stream, Vector, StringBuilder, Range。
  *
  * @author Li.Wei by 2019-08-16
  */
package object collections
