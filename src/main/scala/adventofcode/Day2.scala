package adventofcode

object Day2 {
  def checksum(stringSpreadsheet: String): Int = {
    val spreadsheet = stringSpreadsheet.split("\n").toList.map(_.split(" ").toList.map(_.toInt))

   spreadsheet.foldLeft(0)((total, currentRow) => total + rowDifference(currentRow))
  }

  private def rowDifference(spreadsheetRow: List[Int]): Int = {
    val min = spreadsheetRow.reduceLeft(math.min)
    val max = spreadsheetRow.reduceLeft(math.max)

    max - min
  }
}
