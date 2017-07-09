package com.morizon.example

import com.morizon.example.PKOLoanTestData._
import org.scalacheck.Gen
import org.scalatest.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.{BeforeAndAfterAll, WordSpec}

class PKOLoanTest extends WordSpec with BeforeAndAfterAll {
  val pkoLoan = PKOLoanTestData.pkoLoan
  val overAYear = 13

  "PKO Loan" should {
    def validInterval(range: (Int, Int)) = Gen.choose(range._1, range._2 - 1).sample.get

    val data =
      Table(
        ("amount", "ownContribution", "margin"),
        (validInterval(`0-40k`), validInterval(`10-20%`), 3.94),
        (validInterval(`40-80k`), validInterval(`10-20%`), 2.49),
        (validInterval(`80-120k`), validInterval(`10-20%`), 2.17),
        (validInterval(`120-200k`), validInterval(`10-20%`), 2.05),
        (validInterval(`200-700k`), validInterval(`10-20%`), 1.83),
        (validInterval(over700k), validInterval(`10-20%`), 1.74),

        (validInterval(`0-40k`), validInterval(`20-30%`), 3.67),
        (validInterval(`40-80k`), validInterval(`20-30%`), 2.32),
        (validInterval(`80-120k`), validInterval(`20-30%`), 2.00),
        (validInterval(`120-200k`), validInterval(`20-30%`), 1.88),
        (validInterval(`200-700k`), validInterval(`20-30%`), 1.66),
        (validInterval(over700k), validInterval(`20-30%`), 1.55),

        (validInterval(`0-40k`), validInterval(`30-50%`), 3.54),
        (validInterval(`40-80k`), validInterval(`30-50%`), 2.29),
        (validInterval(`80-120k`), validInterval(`30-50%`), 1.97),
        (validInterval(`120-200k`), validInterval(`30-50%`), 1.85),
        (validInterval(`200-700k`), validInterval(`30-50%`), 1.65),
        (validInterval(over700k), validInterval(`30-50%`), 1.55),

        (validInterval(`0-40k`), validInterval(`50-100%`), 3.94),
        (validInterval(`40-80k`), validInterval(`50-100%`), 2.18),
        (validInterval(`80-120k`), validInterval(`50-100%`), 1.91),
        (validInterval(`120-200k`), validInterval(`50-100%`), 1.79),
        (validInterval(`200-700k`), validInterval(`50-100%`), 1.55),
        (validInterval(over700k), validInterval(`50-100%`), 1.45)
      )

    "find proper margin for every data" in {
      forAll(data) { (amount: Int, ownContribution: Int, margin: Double) =>
        pkoLoan.findMargin(overAYear, amount, ownContribution).get.value shouldBe margin
      }
    }

    for (i <- 1 to 12)
      s"return FirstYearMargin when loan is in the $i month" in {
        pkoLoan.findMargin(i, 100000, 90) shouldBe Some(FirstYearMargin(firstYearValue))
      }
  }
}
