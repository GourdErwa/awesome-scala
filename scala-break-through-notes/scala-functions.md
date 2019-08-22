# Scala 常用函数

目录
=================
   * [a](#a)
      * [aggregate](#aggregate)
      * [andThen](#andthen)
      * [appended](#appended)
      * [appendedAll](#appendedall)
# a
## aggregate
```
trait Collection[A] {
  def aggregate[B](z: => B)(seqop: (B, A) => B, combop: (B, B) => B): B
}
```
![aggregate](./images/scala-functions/aggregate.svg)
## andThen
## appended
## appendedAll
