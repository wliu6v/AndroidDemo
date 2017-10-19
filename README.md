AndroidDemo
==================================

## Color Test

- 显示给定的颜色并提供一个较深色和较浅色。
- 显示给定的颜色是亮色还是暗色（通过文字颜色确认。暗色背景的是白色白色，亮色背景的是黑色文字）
- 调用了一个第三方颜色选择器，来自 [jaredrummler/ColorPicker](https://github.com/jaredrummler/ColorPicker)
- 核心算法是 ColorActivity.isLighterColor() 方法，该方法需要持续调优。目前在网上查到很多可用的方案。

## KotlinInterfaceTest

测试在混用 Java 和 Kotlin 的情况下，当 Kotlin 调用接口时，什么时候可以写成 Lambda 表达式，什么时候不行。

基本结论是：Java 底下的 Interface 和 `setListener(Interface i)` 这种方法会被转为两个对应的 Kotlin 方法，从而 Kotlin 端可以选择是否使用 lambda 表达式进行调用。而 Kotlin 端写的 `setListener(i:Interface)` 这种方法就不能用 Lambda 表达式。

如果想在 Kotlin 使用 Lambda，方法参数形式应该是类似于 `setListener(a: ((Int) -> Unit))` 这种（也就是上面提到的 Java 转成两个对应的 Kotlin 方法其中的另一种）

## EmojiEditTextDemo

see [EmojiEditText 控件会覆盖 XML 中定义的 inputType 属性](http://wliu6v.github.io/2017/EmojiEditText-override-inputtype/)