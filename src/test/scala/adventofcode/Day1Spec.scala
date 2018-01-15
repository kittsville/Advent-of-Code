package adventofcode

import adventofcode.Day1.{inverseCaptcha,halfCaptcha}
import org.specs2._

class Day1Spec extends mutable.Specification {
  "Inverse Captcha specification where" >> {
    "1122 sums to 3" >> {
      inverseCaptcha("1122") must_== 3
    }

    "1111 sums to 4" >> {
      inverseCaptcha("1111") must_== 4
    }

    "1234 sums to 0" >> {
      inverseCaptcha("1234") must_== 0
    }

    "91212129 sums to 9" >> {
      inverseCaptcha("91212129") must_== 9
    }
  }

  "Half Captcha specification where" >> {
    "1212 sums to 6" >> {
      halfCaptcha("1212") must_== 6
    }

    "1221 sums to 0" >> {
      halfCaptcha("1221") must_== 0
    }

    "123425 sums to 4" >> {
      halfCaptcha("123425") must_== 4
    }

    "123123 sums to 12" >> {
      halfCaptcha("123123") must_== 12
    }

    "12131415 sums to 4" >> {
      halfCaptcha("12131415") must_== 4
    }
  }
}
