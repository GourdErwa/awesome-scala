package com.gourd.scala.test.test

import org.scalatest.funsuite.AnyFunSuite


/**
  * @author Li.Wei by 2019/10/24
  */
class SetSuite extends AnyFunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
