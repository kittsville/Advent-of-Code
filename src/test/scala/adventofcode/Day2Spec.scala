package adventofcode

import org.specs2._
import adventofcode.Day2.checksum

class Day2Spec extends mutable.Specification {
  "Corruption Checksum specification where" >> {
    "The spreadsheet must sum to 18" >> {
      val input = "5 1 9 5\n7 5 3\n2 4 6 8"

      checksum(input) must_== 18
    }
  }
}
