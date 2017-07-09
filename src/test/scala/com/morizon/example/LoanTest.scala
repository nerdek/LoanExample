package com.morizon.example

import org.scalatest.Matchers._
import org.scalatest.{BeforeAndAfterAll, WordSpec}

class LoanTest extends WordSpec with BeforeAndAfterAll {

  val firstYearValue = 999.9
  val testedLoan = Loan(firstYearValue)
  val overAYear = 13
  val firstMargin = FurtherMargin((0, 100), (10, 20), 1.01)
  val secondMargin = FurtherMargin((100, 200), (10, 20), 2.02)

  override def beforeAll() {
    testedLoan.addMargin(firstMargin)
    testedLoan.addMargin(secondMargin)
  }

  "findMargin" should {
    "return Failure when no margin is found" in {
      testedLoan.findMargin(overAYear, 100000, 90) shouldBe None
      testedLoan.findMargin(overAYear, 200, 20) shouldBe None
      testedLoan.findMargin(overAYear, 199, 20) shouldBe None
      testedLoan.findMargin(overAYear, 200, 19) shouldBe None
    }

    for (i <- 1 to 12)
      s"return FirstYearMargin when loan is in the $i month" in {
        testedLoan.findMargin(i, 100000, 90) shouldBe Some(FirstYearMargin(firstYearValue))
      }

    "return FurtherMargin when loan is found in the further months" in {
      testedLoan.findMargin(overAYear, 50, 15) shouldBe Some(firstMargin)
      testedLoan.findMargin(overAYear, 150, 15) shouldBe Some(secondMargin)
    }
  }
}
