package adventofcode

import org.specs2._
import adventofcode.Day4.validatePassphrase

class Day4Spec extends mutable.Specification {
  "Passphrase validator specification where" >> {
    "A passphrase without duplicates should be valid" >> {
      validatePassphrase("aa bb cc") must beTrue
    }

    "A passphrase with a duplicate must be invalid" >> {
      validatePassphrase("aa bb aa") must beFalse
    }
  }
}
