package adventofcode

import org.specs2._
import adventofcode.Day2.{corruptionChecksum, divisionChecksum}

class Day2Spec extends mutable.Specification {
  "Corruption Checksum specification where" >> {
    "The spreadsheet must sum to 18" >> {
      val input = "5 1 9 5\n7 5 3\n2 4 6 8"

      corruptionChecksum(input) must_== 18
    }
  }

  "Division Checksum specification where" >> {
    "The spreadsheet must sum to 9" >> {
      val input = "5 9 2 8\n9 4 7 3\n3 8 6 5"

      divisionChecksum(input) must_== 9
    }
  }
}
