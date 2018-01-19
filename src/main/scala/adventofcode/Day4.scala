package adventofcode

object Day4 {
  def validatePassphrase(passphrase: String): Boolean = {
    val phrases = passphrase.split(" +")

    phrases.distinct.length == phrases.length
  }
}
