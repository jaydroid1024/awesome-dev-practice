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

/** The proxy controlling access to the {@link IvoryTower}. */
public class WizardTowerProxy implements WizardTower {
  private static final int NUM_WIZARDS_ALLOWED = 3;
  private final WizardTower tower;
  private int numWizards;
  //被代理方通过构造方法传入
  public WizardTowerProxy(WizardTower tower) {
    this.tower = tower;
  }
  //对象牙塔的访问的代理。第三方通过 enter 方法进入
  @Override
  public void enter(Wizard wizard) {
    //超过三个就不让进了
    if (numWizards < NUM_WIZARDS_ALLOWED) {
      //代理方授权执行操作
      tower.enter(wizard);
      numWizards++;
    } else {
      System.out.println("{} is not allowed to enter!" + wizard);
    }
  }
}
