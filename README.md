
About me: xiaoqianghe


项目介绍:



==================关于Koltin的介绍=========================
一. 关于Koltin

    1. Koltin 的介绍




    2. Koltin 与Java 的比较


=================关于Koltin的修饰词=========================

二.关于修饰词语

    1. private -- 只在声明的范围和同一个模块的子范围可见；


    2. protected -- (只可以用在类或接口的成员上)和 privete 很像，但在子类中也可见；


    3. internal -- (默认使用) 在同一个模块中都可见；


    4. public -- 在任何地方均可见


==================关于Koltin的操作符=========================

三.关于 操作符

    1 ？操作符

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





   2 ?: 操作符

        如果 r 非空，我使用它；否则使用某个非空的值 x

        val l: Int = if (b != null) b.length else -1
        1
        除了完整的 if-表达式，这还可以通过 Elvis 操作符表达：

         val l = b?.length ?: -1
        1
        如果 ?: 左侧表达式非空，elvis操作符就返回其左侧表达式，否则返回右侧表达式。请注意，当且仅当左侧为空时，才会对右侧表达式求值。



   3 !! 操作符


        这会返回一个非空的 b 值 或者如果 b 为空，就会抛出一个 NPE（空指针） 异常：

        val l = b!!.length
        -
        因此，如果你想要一个 NPE，你可以得到它，但是你必须显式要求它，否则它不会不期而至。



    4   ==与===


        ==判断值是否相等，===判断值及引用是否完全相等。


    5 ..符号 以及 in 和 !in 操作符


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




    6  downTo()函数

        倒叙遍历，区间内循环：

        for (i in 4 downTo 1){
            print(i) //倒叙遍历
        }
        // print “4321”


    7  step()函数

         可以进行任意数量的迭代，而不是每次变化都是1

         for (i in 1..4 step 2) print(i) // prints "13"
         for (i in 4 downTo 1 step 2) print(i) // prints "42"

    8  ::符号

         得到类的Class对象

         startActivity(Intent(this@KotlinActivity, MainActivity::class.java))



    9  @符号   很多种用法

         1、限定this的类型

         class User {
             inner class State{
                 fun getUser(): User{
                     //返回User
                     return this@User
                 }
                 fun getState(): State{
                     //返回State
                     return this@State
                 }
             }
         }



         2、作为标签

         跳出双层for

         loop@ for (itemA in arraysA) {
              var i : Int = 0
               for (itemB in arraysB) {
                  i++
                  if (itemB > 2) {
                      break@loop
                  }

                  println("itemB:$itemB")
              }

         }



    9.  as?操作符

           当使用 as 转型的时候，可能会经常出现 ClassCastException。 所以，现在可以使as?安全转型，当转型不成功的时候，它会返回 null。

           注：在使用intent传值的时候，会出现空字符串不能用as强制转型，这是应该使用as?

           val m: Int? = a as? Int


    10  冒号：

        用于类的继承，变量的定义
        1、类型和超类型之间的冒号前要有一个空格
        2、实例和类型之间的冒号前不要空格

        //定义全局变量时
        var str: String? = null

        //类的继承与变量定义
        class TestActivity<T : Serializable>(str: String) : Activity{}


     11. 类型判断符 is

        检查某个实例是否是某个类型，如果判断出属于某个类型，那么判断后的分支中可以直接可当该类型使用，无需显示转换

        fun getStringLength(obj: Any): Int? {
                //obj在&&右边自动动转换成"String"类型
                if (obj is String && obj.length > 0)
                    return obj.length
                return null
            }













