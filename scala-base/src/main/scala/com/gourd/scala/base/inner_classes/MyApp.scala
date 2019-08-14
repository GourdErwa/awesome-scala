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
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
// GraphScala
///////////////////////////////////////////////////////////////////////////////////////////////////////
class GraphScala {

  class Node {
    var connectedNodes: List[Node] = Nil // 存储与其相连的其他节点的列表

    def connectTo(node: Node): Unit = {
      if (!connectedNodes.exists(node.equals)) {
        connectedNodes = node :: connectedNodes
      }
    }
  }

  var nodes: List[Node] = Nil // 图形表示为节点列表

  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }

  {
    val graph1: GraphScala = new GraphScala
    val node1: graph1.Node = graph1.newNode
    val node2: graph1.Node = graph1.newNode
    node1.connectTo(node2) // legal
    val graph2: GraphScala = new GraphScala
    val node3: graph2.Node = graph2.newNode

    /**
      * 类型 graph1.Node 与类型 graph2.Node 完全不同
      * 如果我们希望能够连接不同图形的节点，我们必须通过以下方式[[GraphJava]]更改图形类的初始实现的定义
      * 在 Java 中，node1.connectTo(node3) 示例程序中的最后一行是正确的,在Scala中也可以表示出这种类型，它写成了 Graph#Node
      */
    // node1.connectTo(node3)      // illegal!
  }
}


///////////////////////////////////////////////////////////////////////////////////////////////////////
// GraphJava
///////////////////////////////////////////////////////////////////////////////////////////////////////
class GraphJava {

  class Node {
    var connectedNodes: List[GraphJava#Node] = Nil // 存储与其相连的其他节点的列表

    def connectTo(node: GraphJava#Node): Unit = {
      if (!connectedNodes.exists(node.equals)) {
        connectedNodes = node :: connectedNodes
      }
    }
  }

  var nodes: List[Node] = Nil // 图形表示为节点列表

  def newNode: Node = {
    val res = new Node
    nodes = res :: nodes
    res
  }
  {
    val graph1: GraphJava = new GraphJava
    val node1: graph1.Node = graph1.newNode
    val node2: graph1.Node = graph1.newNode
    node1.connectTo(node2) // legal
    val graph2: GraphJava = new GraphJava
    val node3: graph2.Node = graph2.newNode
    node1.connectTo(node3) // illegal -> legal
  }
}