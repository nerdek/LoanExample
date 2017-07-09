package com.morizon.example

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class IntervalTest extends FunSuite {
  test("number equal to lower bound should be in interval") {
    Interval(0, 10).contains(0) shouldBe true
  }
  test("number smaller than lower bound should not be in interval") {
    Interval(0, 10).contains(-1) shouldBe false
  }
  test("number smaller than upper bound should be in interval") {
    Interval(0, 10).contains(9) shouldBe true
  }
  test("number equal to upper bound should not be in interval") {
    Interval(0, 10).contains(10) shouldBe false
  }
  test("number greater than upper bound should not be in interval") {
    Interval(0, 10).contains(99) shouldBe false
  }
  test("cannot create invalid interval") {
    intercept[IllegalArgumentException](Interval(10, 0))
  }
}
