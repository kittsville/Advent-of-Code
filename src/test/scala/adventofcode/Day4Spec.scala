package adventofcode

import org.specs2._
import adventofcode.Day4.{validatePassphrase, countValidPassphrases}

class Day4Spec extends mutable.Specification {
  "Passphrase validator specification where" >> {
    "A passphrase without duplicates should be valid" >> {
      validatePassphrase("aa bb cc") must beTrue
    }

    "A passphrase with a duplicate must be invalid" >> {
      validatePassphrase("aa bb aa") must beFalse
    }
  }

  "Valid passphrase counter specification where" >> {
    "There are no passphrases" >> {
      countValidPassphrases("") must_== 0
    }

    "There are no valid passphrases" >> {
      val passphrases = "aa bb bb\naa bb cc aa\ncc cc dd aa"

      countValidPassphrases(passphrases) must_== 0
    }

    "There are 2 valid passphrases" >> {
      val passphrases = "aa bb cc\naa bb cc aa\ncc dd ee"

      countValidPassphrases(passphrases) must_== 2
    }
  }
}
