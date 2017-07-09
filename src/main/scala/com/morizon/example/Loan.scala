package com.morizon.example


case class Loan(firstYearValue: Double) {
  private var margins = scala.collection.mutable.Set[FurtherMargin]()

  def addMargin(margin: FurtherMargin) = margins += margin

  def findMargin(month: Int, amount: Int, ownContribution: Int): Option[Margin] = {
    var res: Option[Margin] = None

    def loanMatches(margin: FurtherMargin) =
      margin.ownContribution.contains(ownContribution) && margin.amount.contains(amount)

    if (month <= 12) res = Some(FirstYearMargin(firstYearValue))
    else {
      for (margin <- margins) {
        if (loanMatches(margin))
          res = Some(margin)
      }
    }
    res
  }
}

/**
  * Represent a half-open interval [x,y)
  * x must be smaller than y
  */
case class Interval(from: Int, to: Int) {
  require(from < to, s"$from must be < than $to")

  def contains(x: Int) = if (x >= from && x < to) true else false
}

sealed trait Margin {
  val value: Double
}

case class FirstYearMargin(value: Double) extends Margin

case class FurtherMargin(amount: Interval, ownContribution: Interval, value: Double) extends Margin {
  def this(amount: (Int, Int), ownContribution: (Int, Int), value: Double) =
    this(Interval.tupled(amount), Interval.tupled(ownContribution), value)
}

object FurtherMargin {
  def apply(amount: (Int, Int), ownContribution: (Int, Int), value: Double) =
    new FurtherMargin(amount, ownContribution, value)
}