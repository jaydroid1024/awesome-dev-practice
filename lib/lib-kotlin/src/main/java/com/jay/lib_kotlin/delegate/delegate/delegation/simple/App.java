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

package com.jay.lib_kotlin.delegate.delegate.delegation.simple;

import com.jay.lib_kotlin.delegate.delegate.delegation.simple.printers.CanonPrinter;
import com.jay.lib_kotlin.delegate.delegate.delegation.simple.printers.EpsonPrinter;
import com.jay.lib_kotlin.delegate.delegate.delegation.simple.printers.HpPrinter;

/**
 * The delegate pattern provides a mechanism to abstract away the implementation and control of the
 * desired action. The class being called in this case {@link PrinterController} is not responsible
 * for the actual desired action, but is actually delegated to a helper class either {@link
 * CanonPrinter}, {@link EpsonPrinter} or {@link HpPrinter}. The consumer does not have or require
 * knowledge of the actual class carrying out the action, only the container on which they are
 * calling.
 *
 * <p>In this example the delegates are {@link EpsonPrinter}, {@link HpPrinter} and {@link
 * CanonPrinter} they all implement {@link Printer}. The {@link PrinterController} class also
 * implements {@link Printer}. However neither provide the functionality of {@link Printer} by
 * printing to the screen, they actually call upon the instance of {@link Printer} that they were
 * instantiated with. Therefore delegating the behaviour to another class.
 *
 * <p>委托模式提供了一种机制来抽象出所需操作的实现和控制。 在这种情况下被调用的类 {@link PrinterController} 不负责实际所需的操作， 但实际上委托给一个帮助类
 * {@link CanonPrinter}、{@link EpsonPrinter} 或 {@link HpPrinter}。 消费者不知道或不需要知道执行操作的实际类，只知道他们正在调用的容器。
 *
 * <p>在这个例子中，代表是 {@link EpsonPrinter}、{@link HpPrinter} 和 {@link CanonPrinter}， 它们都实现了 {@link
 * Printer}。 {@link PrinterController} 类还实现了 {@link Printer}。 然而，它们都没有通过打印到屏幕来提供 {@link Printer}
 * 的功能， 它们实际上调用了它们被实例化的 {@link Printer} 实例。因此将行为委托给另一个类。
 */
public class App {

  private static final String MESSAGE_TO_PRINT = "hello world";

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(String[] args) {

    // 委托二人组：打印机控制器、【惠普打印机、佳能打印机、爱普生打印机】
    // 电脑上有三种打印机设备的驱动，分别将打印任务委托给对应的打印机执行具体的打印操作
    PrinterController hpPrinterController = new PrinterController(new HpPrinter());
    PrinterController canonPrinterController = new PrinterController(new CanonPrinter());
    PrinterController epsonPrinterController = new PrinterController(new EpsonPrinter());
    hpPrinterController.print(MESSAGE_TO_PRINT);
    canonPrinterController.print(MESSAGE_TO_PRINT);
    epsonPrinterController.print(MESSAGE_TO_PRINT);
  }
}
