# YinYueTaiPlayKT

> 这是一个Kotlin版的播放器

## MVP架构

本项目采用mvp架构模式，先介绍一下本项目中分包结构

**model：** JavaBean、网络操作、数据库操作都在这里

**view：** 用于链接activity、fragment与presenter之间的链接，多用于接口定义

**presenter：** 一个跟AndroidSDK没有关系的、用于获取和加载数据

**presenter.interf** presnter的接口

**presenter.impl** presnter的接口实现类

**ui：** 用于存放activity、fragment

**ui.activity** 存放activity

**ui.fragment** 存放fragment

**adapter** 存放一些adapter

**util** 存放或引入一些外部工具类

**extension** 自定义kotlin的一些扩展函数类

**base** 提取的一些base基类，用于子类继承

**widget** 存放一些自定义view

**net** 网络请求的简单封装

### BaseActivity
定义了抽象方法abstract
但是定义了，子类复写方法protected后面必须要加open，这点和Java不同

### SplashActivity
给启动页定义一个属性动画
用ViewCompat.animate()//support包里面的

### MainAcitivity
toolBar height ?attr/actionBarSize   根据主题高度设置高度

### 1月3日疑问：
定义方法inline 关键字是什么意思：内的关键字
reified 关键字是什么意思:内联函数，用于约束类型
const 修饰的常量是什么意思

### URLProviderUtils
[Kotlin定义静态方法](https://www.jianshu.com/p/a71f41186aa0)

**object** 修饰类，用于定义静态类

**companion object** 包裹方法，用于定义静态方法（companion object这个关键字实际上会在Util类的内部创建一个伴生类，  
而fun这个方法就是定义在这个伴生类里面的实例方法）

### MRequest
**open** 关键字代码子类可以集成的class ，接口不需要此修饰
