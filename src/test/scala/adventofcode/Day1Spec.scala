package adventofcode

import adventofcode.Day1.inverseCaptcha
import org.specs2._

class Day1Spec extends mutable.Specification {
  "Inverse Captcha specification where" >> {
    "1122 sums to 3" >> {
      inverseCaptcha("1122") must_== 3
    }
  }
}
