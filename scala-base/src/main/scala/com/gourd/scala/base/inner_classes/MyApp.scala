package com.gourd.scala.base.inner_classes

import org.slf4j.LoggerFactory

/** 内部类
  * 在Scala中，一个类可以作为另一个类的成员。
  * 在一些类似 Java 的语言中，内部类是外部类的成员，而 Scala 正好相反，内部类是绑定到外部对象的
  * 采用 Java 将分配相同的类型 Graph.Node; 即 Node 以类 Graph 为前缀。 在Scala中也可以表示出这种类型，它写成了 Graph#Node
  *
  * 具体以 [[GraphScala]]  [[GraphJava]] 示例为主
  *
  * @author Li.Wei by 2019-08-06
  */
object MyApp {
  private val logger = LoggerFactory.getLogger("MyApp")

  def main(args: Array[String]): Unit = {}

  /**
    * 示例
    *
    */
  {
    class Graph {
      // 类 Node 是一个路径依赖类型，因为它嵌套在类 Graph 中。
      class Node {
        var connectedNodes: List[Node] = Nil
        // connectedNodes 中存储的所有节点必须使用同一个 Graph 的实例对象的 newNode 方法来创建。
        def connectTo(node: Node) {
          if (!connectedNodes.exists(node.equals)) {
            connectedNodes = node :: connectedNodes
          }
        }
      }
      var nodes: List[Node] = Nil
      def newNode: Node = {
        val res = new Node
        nodes = res :: nodes
        res
      }
    }
    // node1、node2、node3都是由graph1创建，因此彼此之间可以互相传入connectTo方法
    val graph1: Graph = new Graph
    val node1: graph1.Node = graph1.newNode
    val node2: graph1.Node = graph1.newNode
    val node3: graph1.Node = graph1.newNode
    node1.connectTo(node2)
    node3.connectTo(node1)
    // 编译报错，因为node4是由graph2创建，而node1.connectTo方法只能接收graph1.Node类型的参数
    val graph2: Graph = new Graph
    val node4: graph2.Node = graph2.newNode
    node1.connectTo(node4)      // illegal!，但是在java中这样调用是可以的

    // 如果想要node1连接node4的话，应该这样定义Node中的类型[Graph#Node]
    class Node {
      var connectedNodes: List[Graph#Node] = Nil
      def connectTo(node: Graph#Node) {
        if (!connectedNodes.exists(node.equals)) {
          connectedNodes = node :: connectedNodes
        }
      }
    }
  }
}