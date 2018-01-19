package adventofcode

import adventofcode.Day4.{countNoAnagramPassphrases, countValidPassphrases, hasNoAnagrams, validatePassphrase}
import org.specs2._

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

  "Anagram detector specification where" >> {
    "No anagrams are detected" >> {
      hasNoAnagrams("foo bar") must beTrue
    }

    "Anagram(s) are detected" >> {
      hasNoAnagrams("woo owo") must beFalse
    }

    "Anagram(s) are detected" >> {
      hasNoAnagrams("abcde xyz ecdab") must beFalse
    }

    "No anagrams are detected" >> {
      hasNoAnagrams("a ab abc abd abf abj") must beTrue
    }

    "No anagrams are detected" >> {
      hasNoAnagrams("iiii oiii ooii oooi oooo") must beTrue
    }
  }

  "Valid anagram free passphrase counter specification where" >> {
    "There are no passphrases" >> {
      countNoAnagramPassphrases("") must_== 0
    }

    "There are no anagram-free passphrases" >> {
      val passphrases = "woo owo\naba baa bbb\nkit tik"

      countNoAnagramPassphrases(passphrases) must_== 0
    }

    "There are two anagram-free passphrases" >> {
      val passphrases = "woo owo\nfoo bar\nkit kitty"

      countNoAnagramPassphrases(passphrases) must_== 2
    }
  }
}
