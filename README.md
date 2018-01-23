
About me: xiaoqianghe







项目介绍:

        Koltin 编程语言

        Mvp +Retrofit+ RxJave

        视频播放

        每日精选 + 发现 + 热门 + 我的 4大模块









=====关于Koltin的介绍======

一. 关于Koltin

    1. Koltin 的介绍

        Kotlin，它是JetBrains开发的基于JVM的语言。JetBrains因为创造了
        一个强大的Java开发IDE被大家所熟知。Android Studio，官方的Android IDE，就
        是基于Intellij，作为一个该平台的插件。
        Kotlin是使用Java开发者的思维被创建的，Intellij作为它主要的开发IDE。对于
        Android开发者，有两个有趣的特点：
        对Java开发者来说，Kotlin是非常直觉化的，并且非常容易学习。语言的大部
        分内容都是与我们知道的非常相似，不同的地方，它的基础概念也能迅速地掌
        握它。
        它与我们日常生活使用的IDE无需配置就能完全整合。Android Studio能够非常
        完美地理解、编译运行Kotlin代码。而且对这门语言的支持来正是自于开发了
        这个IDE的公司本身，所以我们Android开发者是一等公民。
        但是这仅仅是开发语言和开发工具之间的整合。相比Java 7的优势到底是什么呢？

        它更加易表现：这是它最重要的优点之一。你可以编写少得多的代码。

        它更加安全：Kotlin是空安全的，也就是说在我们编译时期就处理了各种null的
        情况，避免了执行时异常。如果一个对象可以是null，则我们需要明确地指定
        它，然后在使用它之前检查它是否是null。你可以节约很多调试空指针异常的
        时间，解决掉null引发的bug。

        它是函数式的：Kotlin是基于面向对象的语言。但是就如其他很多现代的语言
        那样，它使用了很多函数式编程的概念，比如，使用lambda表达式来更方便地
        解决问题。其中一个很棒的特性就是Collections的处理方式。

        它可以扩展函数：这意味着我们可以扩展类的更多的特性，甚至我们没有权限
        去访问这个类中的代码。

        它是高度互操作性的：你可以继续使用所有的你用Java写的代码和库，因为两
        个语言之间的互操作性是完美的。甚至可以在一个项目中使用Kotlin和Java两
        种语言混合编程




    2. Koltin 与Java 的比较

            1.易表现 :
                   Jave 编程语言的时候javaBean 类中的每一个属性 都会有对应的get()/set()方法，在koltin中只是这样子就可以实现
                    data class Data{
                        al dataType: String,
                        val text: String,
                        val videoTitle: String,
                        val id: Long,
                        val title: String,
                        val slogan: String?
                        }



            2.空安全:
                 Java 编程语言 的时候 大多的语言都是防御性的  很多时候我们都需要去判断一些异常 如 NullPointerException 空指针异常 等
                 但是 Koltin 语言中很多是空安全的  只需要通过一个简单的操作符 就能实现 大段的判断



            3.函数式支持 ：

                 如 当我实现某一个按钮的点击事件 的时候  我们不用去实现去内部类 而是直接去代码实现我们自己的业务操作逻辑即可


                 这个特性支持在现有类的基础上扩展方法，特别是系统库中的类，因为如果是我们自定义的类，那么扩展和添加方法没有什么差别。

                 方法定义

                 fun getArtict(): Artist? {
                     return null
                 }
                 Kotlin中是以fun关键字声明方法，没有返回值时不需要在方法名后面写任何类型，默认是Unit类型（可写可不写，但其和null不是一回事，所以不写返回值类型或者写了Unit后不能够返回null）。



                 fun String.printStr() {
                     println("printStr: " + this)
                 }

                 var str = "testExtend"
                 str.printStr()
                 上面代码为类String扩展了一个printStr()，这在Java中是不可能的。因为Java中如果既不能改变原有类，又想在其基础上添加方法，就得通过新建类来继承的方式。而现实是Java中只能是单继承，这个机会太珍贵了，更残酷的是有些类还是不能继承的。

            4.转换便捷

                将Java自动转换为Kotlin - JetBrains将IntelliJ集成了一个新功能，将Java转换为Kotlin，节省了大量的时间。而且它也节省了我们重新编写世代代码。





=====关于Koltin的修饰词=====

二.关于修饰词语

    1. private -- 只在声明的范围和同一个模块的子范围可见；


    2. protected -- (只可以用在类或接口的成员上)和 privete 很像，但在子类中也可见；


    3. internal -- (默认使用) 在同一个模块中都可见；


    4. public -- 在任何地方均可见


=====关于Koltin的操作符====

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



    10.  as?操作符

           当使用 as 转型的时候，可能会经常出现 ClassCastException。 所以，现在可以使as?安全转型，当转型不成功的时候，它会返回 null。

           注：在使用intent传值的时候，会出现空字符串不能用as强制转型，这是应该使用as?

           val m: Int? = a as? Int


    11  冒号：

        用于类的继承，变量的定义
        1、类型和超类型之间的冒号前要有一个空格
        2、实例和类型之间的冒号前不要空格

        //定义全局变量时
        var str: String? = null

        //类的继承与变量定义
        class TestActivity<T : Serializable>(str: String) : Activity{}


     12. 类型判断符 is

        检查某个实例是否是某个类型，如果判断出属于某个类型，那么判断后的分支中可以直接可当该类型使用，无需显示转换

        fun getStringLength(obj: Any): Int? {
                //obj在&&右边自动动转换成"String"类型
                if (obj is String && obj.length > 0)
                    return obj.length
                return null
            }



     13. 多行输入符 三个双引号

         三引号的形式用来输入多行文本，也就是说在三引号之间输入的内容将被原样保留，之中的单号和双引号不用转义，其中的不可见字符比如/n和/t都会被保留。

         val str = """
                 one
                 two
                     """
         //等价于
         val str = "one\ntwo"
         val str =  "one" +"\n"+"two"



     14 .$操作符

        字符串可以包含模板表达式，及一小段代码，会求值并把结果包含到字符串中。模板字符串以美元符号$开头，由一个简单的名字构成：

        val value:Int=5;
        val str:String="the value is $value"

        println("itemB:$itemB")

        //字符串模板
        var userInfo = "name:${user.name},  age:$age"



=====关于Koltin的基本类型=====

四. Koltin的基本类型

     1.数字


        Kotlin 提供了如下的内置类型来表示数字（与 Java 很相近）：

        Type	Bit width
        Double	64
        Float	32
        Long	64
        Int	32
        Short	16
        Byte	8



      2. 数组


      数组在 Kotlin 中使用 Array 类来表示，它定义了 get 和 set 函数（按照运算符重载约定这会转变为 []）和 size 属性，以及一些其他有用的成员函数：

          class Array<T> private constructor() {
              val size: Int
              operator fun get(index: Int): T
              operator fun set(index: Int, value: T): Unit

              operator fun iterator(): Iterator<T>
              // ……
          }





=====关于Koltin的回调函数=====






