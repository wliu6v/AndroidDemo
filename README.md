AndroidDemo
==================================

## Color Test

- 显示给定的颜色并提供一个较深色和较浅色。
- 显示给定的颜色是亮色还是暗色（通过文字颜色确认。暗色背景的是白色白色，亮色背景的是黑色文字）
- 调用了一个第三方颜色选择器，来自 [jaredrummler/ColorPicker](https://github.com/jaredrummler/ColorPicker)
- 核心算法是 ColorActivity.isLighterColor() 方法，该方法需要持续调优。目前在网上查到很多可用的方案。