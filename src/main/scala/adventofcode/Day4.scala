package adventofcode

object Day4 {
  def validatePassphrase(passphrase: String): Boolean = {
    val phrases = passphrase.split(" +")

    phrases.distinct.length == phrases.length
  }

  def countValidPassphrases(passphrases: String): Int = {
    if (passphrases.length == 0) {
      return 0
    }

    passphrases.split("\\n").map(validatePassphrase).count(_ == true)
  }

  def hasNoAnagrams(passphrase: String): Boolean = {
    val phrases = passphrase.split(" +").map(_.sorted)

    phrases.distinct.length == phrases.length
  }
}
