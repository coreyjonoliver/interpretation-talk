package calc

import scala.collection.immutable.Stream
import Exceptions._

class Lexer(private var input : Stream[Char]) {

  // The next tokens available.
  private var nextTokens : List[Token] = List.empty

  // The tail (in reverse order) of the next tokens available.
  private var nextTokensTail : List[Token] = List.empty

  // Called when the lexer has seen a full token.
  def emit(token : Token) {
    nextTokensTail = token :: nextTokensTail
  }

  // The current internal state of the lexer.
  private var state : LexerState = INWHITESPACE

  private trait LexerState {
    // Returns the new state after processing a character.
    def process(c : Char) : LexerState

    // Returns the new state after processing end of file.
    def processEOF() : LexerState
  }

  private case object DONE extends LexerState {
    def processEOF() : LexerState = {
      emit(LEX_EOS)
      DONE
    }

    def process(c : Char) : LexerState = {
      throw ImpossibleException()
    }
  }

  private case class INNUMERAL(buf : List[Char]) extends LexerState {

    def processEOF() : LexerState = {
      emit(LEX_NUMERAL(buf.reverse.mkString.toInt))
      DONE
    }

    def process(c : Char) : LexerState = {
      if (c.isDigit) {
        return INNUMERAL(c :: buf)
      }

      emit(LEX_NUMERAL(buf.reverse.mkString.toInt))

      val old = input
      input = c #:: old
      INWHITESPACE
    }
  }

  private case object INWHITESPACE extends LexerState {

    def processEOF () : LexerState = {
      emit(LEX_EOS)
      DONE
    }

    def process(c : Char) : LexerState = {
      if (c.isWhitespace)
        return INWHITESPACE

      if (c.isDigit) {
        return INNUMERAL(List(c))
      }

      c match {
        case '+' =>
          emit(LEX_PLUS)
          INWHITESPACE
        case '-' =>
          emit(LEX_MINUS)
          INWHITESPACE
        case '*' =>
          emit(LEX_TIMES)
          INWHITESPACE
        case '/' =>
          emit(LEX_DIVIDE)
          INWHITESPACE
        case '(' =>
          emit(LEX_LPAREN)
          INWHITESPACE
        case ')' =>
          emit(LEX_RPAREN)
          INWHITESPACE
      }
    }
  }

  // Processes characters until the lexer emits tokens.
  private def loadTokens() {
    if (!nextTokens.isEmpty)
      return

    if (!nextTokensTail.isEmpty) {
      nextTokens = nextTokensTail.reverse
      nextTokensTail = List.empty
      return
    }

    if (input.isEmpty) {
      state = state.processEOF()
      nextTokens = nextTokensTail.reverse
      nextTokensTail = List.empty
      return
    }

    while (nextTokensTail.isEmpty && !input.isEmpty) {
      val c = input.head
      input = input.tail
      state = state.process(c)
    }

    if (input.isEmpty)
      state = state.processEOF()

    nextTokens = nextTokensTail.reverse
    nextTokensTail = List.empty
  }

  // Returns the next available token without consuming it.
  def peek() : Token = {
    loadTokens()
    nextTokens.head
  }

  // Pulls the next token from the input and returns it.
  def next() : Token = {
    loadTokens()
    val t = nextTokens.head
    nextTokens = nextTokens.tail
    t
  }

  def eatLPAREN() =
    next() match {
      case LEX_LPAREN => {}
      case t => throw ParseException("expected: '('; got: " + t)
    }

  def eatRPAREN() =
    next() match {
      case LEX_RPAREN => {}
      case t => throw ParseException("expected: ')'; got: " + t)
    }
}