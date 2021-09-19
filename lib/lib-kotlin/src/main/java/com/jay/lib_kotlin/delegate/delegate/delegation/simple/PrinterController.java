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

/**
 * Delegator Class to delegate the implementation of the Printer. This ensures two things: - when
 * the actual implementation of the Printer class changes the delegation will still be operational -
 * the actual benefit is observed when there are more than one implementors and they share a
 * delegation control
 */
public class PrinterController implements Printer {
  private final Printer printer;
  public PrinterController(Printer printer) {
    this.printer = printer;
  }
  /**
   * 此方法是从 {@link Printer} 实现的，在提供实现时它会调用通过构造函数传递的委托方的 print 方法。
   * 这意味着委托关系一旦确定后，调用者不关心实现类，只关心拥有的打印机控制器就行。
   */
  @Override
  public void print(String message) {
    printer.print(message);
  }
}
