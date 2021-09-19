/*
 * The MIT License
 * Copyright © 2014-2021 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.jay.lib_kotlin.delegate.proxy;

/**
 * A proxy, in its most general form, is a class functioning as an interface to something else. The
 * proxy could interface to anything: a network connection, a large object in memory, a file, or
 * some other resource that is expensive or impossible to duplicate. In short, a proxy is a wrapper
 * or agent object that is being called by the client to access the real serving object behind the
 * scenes.
 *
 * <p>The Proxy design pattern allows you to provide an interface to other objects by creating a
 * wrapper class as the proxy. The wrapper class, which is the proxy, can add additional
 * functionality to the object of interest without changing the object's code.
 *
 * <p>In this example the proxy ({@link WizardTowerProxy}) controls access to the actual object (
 * {@link IvoryTower}).
 *
 * <p>代理，就其最一般的形式而言，是一个充当其他事物接口的类。 代理可以连接到任何东西：网络连接、内存中的大对象、文件或其他一些昂贵或无法复制的资源。
 * 简而言之，代理是一个包装器或代理对象，客户端正在调用它来访问幕后的真实服务对象。
 *
 * <p>代理设计模式允许您通过创建包装类作为代理来为其他对象提供接口。 作为代理的包装类可以向感兴趣的对象添加附加功能，而无需更改对象的代码。
 *
 * <p>在此示例中，代理 ({@link WizardTowerProxy}) 控制对实际对象 ({@link IvoryTower}) 的访问。
 */
public class App {

  /** Program entry point. */
  public static void main(String[] args) {

    // 委托和代理的根本区别是什么
    //
    // 委托是指委托人与被委托人签订委托书，被委托人在规定的权限范围内进行民事活动的行为。而代理是指代理人在代理权限内，以被代理人的名义实施民事法律的行为。二者的区别如下：
    //
    // 1、行使权利的名义不同，代理包括委托代理、法定代理、指定代理三种类型，法定代理和指定代表是由法律规定的。而委托是指双方当事人在诚信的基本上，通过合同的方式确定进行的民事法律活动。
    //
    // 2、从事的事务不同。代理涉及的行为以意思表示为要素，故代理的一定是民事法律行为；委托不要求以“意思表示”为要素，因此委托从事的行为可以是纯粹的事务性行为。
    // 3、代理涉及三方当事人，即被代理人、代理人、第三人；委托则属于双方当事人之间的关系，即委托人、受托人。

    // 代理三人组：巫师塔代理、象牙塔、巫师
    // 巫师要进入塔内修炼法术，象牙塔只能通过代理访问，确保只有前三个巫师可以进入。
    WizardTowerProxy proxy = new WizardTowerProxy(new IvoryTower());
    proxy.enter(new Wizard("Red wizard"));
    proxy.enter(new Wizard("White wizard"));
    proxy.enter(new Wizard("Black wizard"));
    proxy.enter(new Wizard("Green wizard"));
    proxy.enter(new Wizard("Brown wizard"));
  }
}
