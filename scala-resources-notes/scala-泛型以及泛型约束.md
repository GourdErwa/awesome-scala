# 边界(Bounds)
 `[T <: Comparable[T]]`
- `<:` ：指明上界，表达了泛型的类型必须是"某种类型"或某种类型的"子类"
- `>:` ：指明下界，表达了泛型的类型必须是"某种类型"或某种类型的"父类"

# 协变/逆变
- `[+T]`
- `[-T]`
## 视图界定 View Bounds （已废弃该语法）
`[T <% Comparable[T]]`
`<%` : 对上边界的加强版，可以利用implicit隐式转换将实参类型转换成目标类型

## 上下文界定Context Bounds
`[T : Ordering]`
说明存在一个隐式的值 `Ordering[T](implicit ordered: Ordering[T])`

针对创建泛型数组的上下文界定：
`[T : ClassTag]`
`[T : Manifest]`