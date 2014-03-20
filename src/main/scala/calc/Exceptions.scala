package calc

object Exceptions {
  // Thrown when code reaches a supposedly impossible state -- a bug.
  case class ImpossibleException() extends RuntimeException

  // Thrown when the input is not a valid expression.
  case class ParseException (reason : String) extends RuntimeException
}
