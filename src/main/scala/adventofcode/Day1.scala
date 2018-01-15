package adventofcode

object Day1 {
  def inverseCaptcha(captcha: String): Int = {
    val digitList = captcha.map(_.asDigit).toList

    val circularDigitList = digitList :+ digitList.head

    circularDigitList.foldLeft((0, -1))(addDigits)._1
  }

  private def addDigits(left: (Int, Int), right: Int): (Int, Int) = {
    right match {
      case left._2 => (left._1 + right, right)
      case _ => (left._1, right)
    }
  }
}
