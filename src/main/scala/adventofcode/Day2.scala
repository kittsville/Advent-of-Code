package adventofcode

object Day2 {
  def corruptionChecksum(stringSpreadsheet: String): Int = {
    val spreadsheet = parseSpreadsheet(stringSpreadsheet)

   spreadsheet.foldLeft(0)((total, currentRow) => total + rowDifference(currentRow))
  }

  def divisionChecksum(stringSpreadsheet: String): Int = {
    val spreadsheet = parseSpreadsheet(stringSpreadsheet)

    spreadsheet.foldLeft(0)((total, currentRow) => total + rowDivision(currentRow))
  }

  private def parseSpreadsheet(stringSpreadsheet: String): List[List[Int]] = {
    stringSpreadsheet.split("\n").toList.map(_.split("\\s+").toList.map(_.toInt))
  }

  private def rowDivision(spreadsheetRow: List[Int]): Int = {
    val sortedRow = spreadsheetRow.sorted
    val divisibleNumbers = getDivisibleNumbers(sortedRow.head, sortedRow.tail)

    divisibleNumbers._2 / divisibleNumbers._1
  }

  private def getDivisibleNumbers(denominator: Int, numerators: List[Int]): (Int, Int) = {
    numerators.filter(_ % denominator == 0) match {
      case numerator :: _ => (denominator, numerator)
      case _ => getDivisibleNumbers(numerators.head, numerators.tail)
    }
  }

  private def rowDifference(spreadsheetRow: List[Int]): Int = {
    val min = spreadsheetRow.reduceLeft(math.min)
    val max = spreadsheetRow.reduceLeft(math.max)

    max - min
  }
}
