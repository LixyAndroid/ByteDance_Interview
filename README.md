# ByteDance_Interview
字节跳动一面Android岗


## 1，自我介绍一下
## 2，然后就先聊聊java相关的，内存分配  内存模型
## 3，内存溢出
## 4，TCP和UDP 区别  UDP没有回答好，突然这些都知道，但是面试的时候没有回答上来
，只回答了UDP，简单的，但是面试官 紧接着问，简单怎么理解

### 面向连接的TCP
“面向连接”就是在正式通信前必须要与对方建立起连接。比如你给别人打电话，必须等线路接通了、对方拿起话筒才能相互通话。
TCP（Transmission Control Protocol，传输控制协议）是基于连接的协议，也就是说，在正式收发数据前，必须和对方建立可靠的连接。一个TCP连接必须要经过三次“对话”才能建立起来，其中的过程非常复杂，我们这里只做简单、形象的介绍，你只要做到能够理解这个过程即可。

### 面向非连接的UDP协议
“面向非连接”就是在正式通信前不必与对方先建立连接，不管对方状态就直接发送。这与现在风行的手机短信非常相似：你在发短信的时候，只需要输入对方手机号就OK了。
UDP（User Data Protocol，用户数据报协议）是与TCP相对应的协议。它是面向非连接的协议，它不与对方建立连接，而是直接就把数据包发送过去！
UDP 适用于一次只传送少量数据、对可靠性要求不高的应用环境。比如，我们经常使用“ping”命令来测试两台主机之间TCP/IP通信是否正常，其实 “ping”命令的原理就是向对方主机发送UDP数据包，然后对方主机确认收到数据包，如果数据包是否到达的消息及时反馈回来，那么网络就是通的。例如， 在默认状态下，一次“ping”操作发送4个数据包。大家可以看到，发送的数据包数量是4包，收到的也是4包（因为对方主机收到后会发回一 个确认收到的数据包）。这充分说明了UDP协议是面向非连接的协议，没有建立连接的过程。正因为UDP协议没有连接的过程，所以它的通信效果高；但也正因为如此，它的可靠性不如TCP协议高。QQ就使用UDP发消息，因此有时会出现收不到消息的情况。
 

## 5，抽象类和接口，接口回答的不好
1、概念不一样。接口是对动作的抽象，抽象类是对本质的抽象。
抽象类表示的是，这个对象是什么。接口表示的是，这个对象能做什么。比如，男人，女人，这两个类（如果是类的话……），他们的抽象类是人。说明，他们都是人。人可以吃东西，狗也可以吃东西，你可以把“吃东西”定义成一个接口，然后让这些类去实现它。
所以，在高级语言上，一个类只能继承一个类（抽象类）(正如人不可能同时是生物和非生物)，但是可以实现多个接口(吃饭接口、走路接口)。
2、使用不一样：
 a.抽象类 和 接口 都是用来抽象具体对象的. 但是接口的抽象级别最高
b.抽象类可以有具体的方法 和属性,  接口只能有抽象方法和不可变常量
c.抽象类主要用来抽象类别,接口主要用来抽象功能.
d.抽象类中，且不包含任何实现，派生类必须覆盖它们。接口中所有方法都必须是未实现的。
 e.接口是设计的结果 ，抽象类是重构的结果
3、使用方向：当你关注一个事物的本质的时候，用抽象类；当你关注一个操作的时候，用接口。
注意：抽象类的功能要远超过接口，但是，定义抽象类的代价高。因为高级语言来说（从实际设计上来说也是）每个类只能继承一个类。在这个类中，你必须继承或编写出其所有子类的所有共性。虽然接口在功能上会弱化许多，但是它只是针对一个动作的描述。而且你可以在一个类中同时实现多个接口。在设计阶段会降低难度的。

## 6，Android的Touch事件分发机制，拦截，响应等这个基本没回答上来，刚开始以为要问handler的，本就没认真了解过  然后以这块知识没人深入了解过而gg。 很奇怪，问自定义view也可以呀，这个好歹可以说点（白眼）。

相关Touch事件的方法
1、public boolean dispatchTouchEvent(MotionEvent ev) —事件分发方法，分发Event所调用
2、public boolean onInterceptTouchEvent(MotionEvent ev)—事件拦截方法，拦截Event所调用
3、public boolean onTouchEvent(MotionEvent event)　—事件响应方法，处理Event所调用
拥有上述事件的类
1、Activity类（Activity及其各种继承子类）
　　　　dispatchTouchEvent()、onTouchEvent()
2、ViewGroup类（LinearLayout、FrameLayout、ListView等.....）
　　　　dispatchTouchEvent()、onInterceptTouchEvent()、onTouchEvent()
3、View类（Button、TextView等.....）
　　　　dispatchTouchEvent()、onTouchEvent()
### PS：需要特别注意一点就是ViewGroup中额外拥有onInterceptTouchEvent()方法，其他两个方法为这三种类所共同拥有。
方法的简单用途解析
我们可以发现这三个方法的返回值都为boolean类型，其实它们就是通过返回值来决定下一步的传递处理方向。
1、dispatchTouchEvent()  ——用来分发事件所用
　　该方法会将根元素的事件自上而下依次分发到内层子元素中，直到被终止或者到达最里层元素，该方法也是采用一种隧道方式来分发。在其中会调用onInterceptTouchEvent()和onTouchEvent()，一般不会去重写。
　　返回false则不拦截继续往下分发，如果返回true则拦截住该事件不在向下层元素分发，在dispatchTouchEvent()方法中默认返回false。
2、onInterceptTouchEvent()  ——用来拦截事件所用
　　该方法在ViewGroup源代码中实现就是返回false不拦截事件，Touch事件就会往下传递给其子View。如果我们重写该方法并且将其返回true，该事件将会被拦截，并且被当前ViewGroup处理，调用该类的onTouchEvent()方法。
3、onTouchEvent()  ——用来处理事件
　　返回true则表示该View能处理该事件，事件将终止向上传递（传递给其父View）
返回false表示不能处理，则把事件传递给其父View的onTouchEvent()方法来处理

## 7，Android activity生命周期，onStop 和onPause的区别，生命周期也是想着说的，说了几个方法，没具体的说，然后就问onStop 和onPause的区别,模棱两可说了点，感觉不怎么对，



关于activity的四个状态： 
running-poused-stopped-killed

running->当前显示在屏幕的activity(位于任务栈的顶部)，用户可见状态。
poused->依旧在用户可见状态，但是界面焦点已经失去，此Activity无法与用户进行交互。
stopped->用户看不到当前界面,也无法与用户进行交互 完全被覆盖.
killed->当前界面被销毁，等待这系统被回收

oncreate()->onstart()->onResume()->onRestart()->onPouse()->onStop()->onDestory()



### 拓展：问题1：onStart和onResume、onPause以及onStop的实质区别？

  onStart和onStop是从Activity是否可见这个角度来回调的，而onResume和onPause是从Activity是否位于前台这个角度来回调的。
  onstart表示Activity可见，但是还不能与用户进行交互，可以理解为Activity已经显示出来了，但是我们还看不见。
  onStop表示Activity即将停止，此时可以做一些稍微重量级的回收工作，但不能太耗时，此时Activity已经变得不可见。
  onResume表示此时Activity从后台切换到前台，可以与用户进行交互，于onstart相比，onStart和onResume都表示Activity可见，但onstart的时候Activity还在后台，而onResume时Activity从后台切换到前台。
  onPause表示Activity正在停止，此时Activity切换到后台，不能与用户进行交互。不能再onPause中做重量级的操作。
  从Activity的整个生命周期来看，onCreate和ondestory是配对的，分别标识着Activity的创建与销毁，并且只能有一次被调用。
  从Activity的可见来说，onstart和onStop是配对的，随着用户的操作或者设备屏幕的点亮与熄灭，这两个方法可能被多次调用。
  从Activit是否在前台来说，onResume和onPause是配对的，随着用户的操作或者设备屏幕的点亮与熄灭，这两个方法可能多次被调用。



#### 问题2：有两个Activity，分别为Activity A和Activity B，用A启动B ,B 的onResume和A的onPause哪个先执行?

  追踪android的源码可知，在新的activity启动之前，栈顶的Activity需要先onPause后，新的Activity才能执行。因此在启动ActivityB之前先执行Activity A的onPause，然后启动Activity B 执行Activity B的onResume。


## 8，然后开始写代码；

单例设计模式，线程安全的,写过几遍，很快速就写了出来
快速排序，磕磕绊绊也没写好，最后放弃了 
两段代码已写出，在上面

# 写在最后：
这是第一次面试，对于一个只接触4个月互联网的萌新来说确实有点残酷了，（不过一般给面试管都说1年开发经验了） 也是自己努力不够。主要呈现的问题就是，一个问题问出来，也能说点，但是再深入一点就不知道了，深入不够。
还有就是刷题部分，这个是刚刷一点，需要继续刷下去。下一步，明天是网易的当面的，更是紧张点。不过没关系，不过继续上学了，也知道自己该怎么努力了。
两大点，java 基础还不行，Android 各方面知识还不完善，需要就是深入源码，自己要去分析。 再以后的项目中，一点要深入，每段代码怎么来的，为什么要这么写，一定弄要明白！！！ 




