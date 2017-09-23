/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 石头语言.语法;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class 方法测试类 {

  @Test
  public void 加() {
    assertEquals(1, new 方法("+", Arrays.asList(1)).运行());
    assertEquals(6, new 方法("+", Arrays.asList(1, 2, 3)).运行());
  }

  @Test
  public void 判断assertEquals() {
    方法 相等 = new 方法("=", Arrays.asList(1, 2));
    assertEquals(false, 相等.运行());

    相等.参数 = Arrays.asList(2, 2);
    assertEquals(true, 相等.运行());

    相等.参数 = Arrays.asList(2, 2, 1);
    assertEquals(false, 相等.运行());
  }
}