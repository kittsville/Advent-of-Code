package adventofcode

object Day1 {
  def inverseCaptcha(captcha: String): Int = {
    val digitList = toDigitList(captcha)

    val circularDigitList = digitList :+ digitList.head

    circularDigitList.foldLeft((0, -1))(addDigits)._1
  }

  def halfCaptcha(captcha: String): Int = {
    val digitList = toDigitList(captcha)

    val digitPairs = splitFlipZip(digitList)

    digitPairs.foldLeft(0)(sumMatchingPairs)
  }

  private def splitFlipZip[A](list: List[A]): List[(A, A)] = {
    list.splitAt(list.length / 2) match {
      case (left, right) => list zip right ++ left
    }
  }

  private def sumMatchingPairs(total: Int, digitPair: (Int, Int)): Int = {
    if (digitPair._1 == digitPair._2 ) {
      total + digitPair._1
    } else {
      total
    }
  }

  private def toDigitList(rawCaptcha: String): List[Int] = rawCaptcha.map(_.asDigit).toList

  private def addDigits(left: (Int, Int), right: Int): (Int, Int) = {
    right match {
      case left._2 => (left._1 + right, right)
      case _ => (left._1, right)
    }
  }
}
