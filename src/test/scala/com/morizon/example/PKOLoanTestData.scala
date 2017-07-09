package com.morizon.example

object PKOLoanTestData {
  val firstYearValue = 1.1
  val pkoLoan = Loan(firstYearValue)

  // loan amounts ranges
  val `0-40k`: (Int, Int) = (0, 40000)
  val `40-80k`: (Int, Int) = (40000, 80000)
  val `80-120k`: (Int, Int) = (80000, 120000)
  val `120-200k`: (Int, Int) = (120000, 200000)
  val `200-700k`: (Int, Int) = (200000, 700000)
  val over700k: (Int, Int) = (700000, Int.MaxValue)

  // own contribution ranges
  val `10-20%`: (Int, Int) = (10, 20)
  val `20-30%`: (Int, Int) = (20, 30)
  val `30-50%`: (Int, Int) = (30, 50)
  val `50-100%`: (Int, Int) = (50, 100)

  pkoLoan.addMargin(FurtherMargin(`0-40k`, `10-20%`, 3.94))
  pkoLoan.addMargin(FurtherMargin(`40-80k`, `10-20%`, 2.49))
  pkoLoan.addMargin(FurtherMargin(`80-120k`, `10-20%`, 2.17))
  pkoLoan.addMargin(FurtherMargin(`120-200k`, `10-20%`, 2.05))
  pkoLoan.addMargin(FurtherMargin(`200-700k`, `10-20%`, 1.83))
  pkoLoan.addMargin(FurtherMargin(over700k, `10-20%`, 1.74))

  pkoLoan.addMargin(FurtherMargin(`0-40k`, `20-30%`, 3.67))
  pkoLoan.addMargin(FurtherMargin(`40-80k`, `20-30%`, 2.32))
  pkoLoan.addMargin(FurtherMargin(`80-120k`, `20-30%`, 2.00))
  pkoLoan.addMargin(FurtherMargin(`120-200k`, `20-30%`, 1.88))
  pkoLoan.addMargin(FurtherMargin(`200-700k`, `20-30%`, 1.66))
  pkoLoan.addMargin(FurtherMargin(over700k, `20-30%`, 1.55))

  pkoLoan.addMargin(FurtherMargin(`0-40k`, `30-50%`, 3.54))
  pkoLoan.addMargin(FurtherMargin(`40-80k`, `30-50%`, 2.29))
  pkoLoan.addMargin(FurtherMargin(`80-120k`, `30-50%`, 1.97))
  pkoLoan.addMargin(FurtherMargin(`120-200k`, `30-50%`, 1.85))
  pkoLoan.addMargin(FurtherMargin(`200-700k`, `30-50%`, 1.65))
  pkoLoan.addMargin(FurtherMargin(over700k, `30-50%`, 1.55))

  pkoLoan.addMargin(FurtherMargin(`0-40k`, `50-100%`, 3.94))
  pkoLoan.addMargin(FurtherMargin(`40-80k`, `50-100%`, 2.18))
  pkoLoan.addMargin(FurtherMargin(`80-120k`, `50-100%`, 1.91))
  pkoLoan.addMargin(FurtherMargin(`120-200k`, `50-100%`, 1.79))
  pkoLoan.addMargin(FurtherMargin(`200-700k`, `50-100%`, 1.55))
  pkoLoan.addMargin(FurtherMargin(over700k, `50-100%`, 1.45))

}
