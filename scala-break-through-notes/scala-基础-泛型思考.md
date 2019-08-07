# Scala 泛型思考
## 参考文档
- [java泛型的理解](http://hongjiang.info/java-generics/)
- [stack overflow-What is PECS (Producer Extends Consumer Super)?](https://stackoverflow.com/questions/2723397/what-is-pecs-producer-extends-consumer-super?rq=1)
# 疑问1

`public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable {}`
                
`public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {}`

上面的泛型表达式问题可以通过： [官方 Java Sun](http://java.sun.com/j2se/1.5/pdf/generics-tutorial.pdf) , [中文版](http://blog.csdn.net/explorers/archive/2005/08/15/454837.aspx) 来获取答案

# PECS 原则
 (producer-extends, consumer-super) 或者也叫 Get and Put 原则
 
假设您有一个方法，它将事物的集合作为参数，但您希望它比仅接受 `Collection<Thing>` 更灵活 

- 案例1：遍历集合并对每个元素执行操作
然后Collection是生产者，所以你应该使用 `Collection<? extends Thing>`

原因是 `Collection<? extends Thing>` 可以保存任何子类型Thing，因此每个元素在执行操作时都会表现为Thing
（您实际上无法添加任何内容`Collection<? extends Thing>`，因为您无法在运行时知道该集合的哪个特定子类型Thing）

- 案例2：您想要将元素添加到集合中
然后Collection是消费者，所以你应该使用`Collection<? super Thing>`

无论实际的参数化类型是什么，`Collection<? super Thing>`都可以随时保持Thing
在这里你不关心Collection中已有的内容，只要它允许Thing添加; 这就是`? super Thing`保证

## 以下是如何确定您的类型是否 ParametricType[T] 可以/不能 协变/逆变：
- 当一个类型不调用泛型作为参数的类型的方法时，它可以是协变的。反之。
原型的例子：Seq[+A]，Option[+A]，Future[+T]
- 如果需要返回泛型时，则它不能是逆变的。
原型的例子：Function1[-T1, +R]，CanBuildFrom[-From, -Elem, +To]，OutputChannel[-Msg]
