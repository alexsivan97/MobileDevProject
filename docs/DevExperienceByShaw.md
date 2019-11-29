
**记录一下开发过程中遇到的问题**

## 参考资料
- [material design](https://md.maxoxo.design/components/cards.html#)
- [关于material design的介绍](http://www.uisdc.com/material-design-knowledge)
- [material design API](https://material-io.cn/develop/android/components/material-card-view/)

# 组件和组件库

## 底部导航栏 BottomNavigationView
- 里面的item通过menu文件配置；
- res下新建一个menu文件夹，将每个menu.xml放在里面
- menu文件内通过item配置各个按钮
- 每个item配置icon和title
- 导航栏控件有默认的区分active和inactive的图标，如果想自定义要配置一个selector
- as中有自带的icon，在drawable下new-Vector Asset选择，可以自由修改颜色和大小
- 也可以在java文件中通过addItem(new BottomNavigationItem)来添加

## 卡片 MaterialCardView
- 每个卡片里面可以包含一个组件，它只是一张卡片，而不是卡片列表
- 卡片可以在z轴上上浮（app:cardElevation="10dp"）
- 设置margin让它更好看

## 选项卡 TabLayout
- tablayout+viewpager+fragment来实现选项卡界面切换。
- fragment需要自己配置并绘制布局，如果每个选项内容不同还要配置不同的fragment

## 列表 RecyclerView
- 手动注册点击时间
- 配置adapter和viewholder来使用
- item的布局也要额外生成
- **遇到的终极bug：item的高度不能match_parent!**

## 一个组件库 com.github.rey5137:material:1.2.5
- 最新的1.3.0引入后会发生错误，issue中提供的方法是改成1.2.5，的确可以成功引入
- 尝试了一下EditText，提供的wiki不太会用，不知道是不是版本号的问题


# 开发技巧

## intent传递信息
- 如果需要携带对象作为putExtra的内容，那么这个对象要实现序列化

## 组件自动绑定库 butterknife
- 替代findViewById，减少冗余代码，貌似还能提高性能
- 配置：之前网上的8.8.0版本和androidx有不兼容的地方，经查资料改成10.0.0就可以使用了。
```
implementation 'com.jakewharton:butterknife:10.0.0'
annotationProcessor 'com.jakewharton:butterknife-compiler:10.0.0'
```
- as插件：File | Settings | Plugins 搜索butterknife，下载安装Android butterknife Zeleney，就可以在generate选项中自动生成组件引用和绑定对应的ui控件。快捷键alt+insert。

# bug调试

## R不存在
- 默认的activity文件直接放在java包下，如果要新建一个activity package，需要在activity.java中手动导入r包
- 在File | Settings | Editor | General | Auto Import中可以勾选自动导入
