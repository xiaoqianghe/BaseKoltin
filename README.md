
1.open
open 注解与java 中的 final相反:它允许别的类继承这个类。默认情形下，kotlin 中所有的类都是 final 对应
Effective Java ：Design and document for inheritance orelse prohibit i


2. 修饰词语

private -- 只在声明的范围和同一个模块的子范围可见；
protected -- (只可以用在类或接口的成员上)和 privete 很像，但在子类中也可见；
internal -- (默认使用) 在同一个模块中都可见；
public -- 在任何地方均可见\

3.关于 操作符




   3.1 ？操作符

    表示这个对象可能为空

    //在变量类型后面加上问号，代表该变量是可空变量
    var name: String? = "zhangsan"

    /**
     * 如果str不能转为Int类型，则返回null
     */
    fun parseInt(str: String): Int? {
      // (代码略)
    }

    b?.length //如果 b非空，就返回 b.length ，否则返回 null，这个表达式的类型是 Int? 。





   3.2 ?: 操作符

    如果 r 非空，我使用它；否则使用某个非空的值 x

    val l: Int = if (b != null) b.length else -1
    1
    除了完整的 if-表达式，这还可以通过 Elvis 操作符表达：

     val l = b?.length ?: -1
    1
    如果 ?: 左侧表达式非空，elvis操作符就返回其左侧表达式，否则返回右侧表达式。请注意，当且仅当左侧为空时，才会对右侧表达式求值。



   3.3 !! 操作符


    这会返回一个非空的 b 值 或者如果 b 为空，就会抛出一个 NPE（空指针） 异常：

    val l = b!!.length
    -
    因此，如果你想要一个 NPE，你可以得到它，但是你必须显式要求它，否则它不会不期而至。



    3.4   ==与===


    ==判断值是否相等，===判断值及引用是否完全相等。


    3.5 ..符号 以及 in 和 !in 操作符


    ..代表从x到y，包括x和y,这是一个闭区间运算符，而until则是半闭区间运算符，代表从a到b范围内所有的值，包括a和不包括b。
    in代表在一个区间中，！in代表不在一个区间中。

    使用 in 运算符来检查某个数字是否在指定区间内

    if (i in 1..10) { // 等价于 1 <= i && i <= 10
        println(i)
    }
    //使用until函数,创建一个不包括其结束元素的区间
    for (i in 1 until 10) {   // i in [1, 10) 排除了 10
         println(i)
    }












