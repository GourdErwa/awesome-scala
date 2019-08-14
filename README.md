# scala-advanced
scala 基础、常用类库使用

# 构建环境
- Intellij 2019.2
- Gradle 5.5.1
- Java 11
- Scala 2.13.0
> 说明：
> - 项目依赖scala包需2.13.0版本编译，避免因版本差异导致 ClassNotFoundException 异常

# 项目结构
## 模块命名
- `scala-base` scala语言基础
- `scala-app-${lib-name}` 模块为第三方类库应用实例
## 模块说明
|模块|说明|
|:---|:---|
|[scala-base](./scala-base)|语言核心特性，特性深度分析示例|
|[scala-break-through-notes](./scala-break-through-notes)|细节知识笔记|
|[scala-app-json](./scala-app-json)|JSON相关操作|
|[scala-app-shapeless](./scala-app-shapeless)|shapeless 库相关操作|
|[scala-app-cats](./scala-app-cats)|cats 库相关操作|

## 编码风格
* 模块内容参考模块下`README.md`文件声明
* 包内容参考 `package` 注释说明

# 参考资源
## 官方文档
* [scala-lang-语言核心特性](https://docs.scala-lang.org/zh-cn/tour/tour-of-scala.html)
* [scala-lang-特性深度分析](https://docs.scala-lang.org/zh-cn/overviews/)
* [scala-lang-java基础快速快速入门](https://docs.scala-lang.org/zh-tw/tutorials/scala-for-java-programmers.html)
* [scala-lang-语法速查](https://docs.scala-lang.org/zh-cn/cheatsheets/index.html)
* [scala-lang-代码风格引导](https://docs.scala-lang.org/style/)
* [scala-lang-api](https://docs.scala-lang.org/api/all.html)
* [scala-lang-常见问题及答案](https://docs.scala-lang.org/tutorials/FAQ/index.html)


## scala-style-guide
* [databricks Scala Coding Style Guide](https://github.com/databricks/scala-style-guide)

## 第三方资源
* [twitter-effective-scala](http://twitter.github.io/effectivescala/index-cn.html)
* [李浩毅StrategicScalaStyle-列举了面对各种状况下应该选择的语言功能](http://www.lihaoyi.com/post/StrategicScalaStylePrincipleofLeastPower.html)
* [underscore.io-开源scala相关书籍](https://underscore.io/training/)
* [underscore.io-shapeless-guide-Scala泛型编程库](https://underscore.io/books/shapeless-guide/)

# 学习资源
[Scala Exercises-在线学习scala不同的类库使用](https://www.scala-exercises.org/)