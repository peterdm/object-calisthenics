package common

/**
 * Thrown for naughty behaviors
 */
class UnauthorizedException(msg: String) extends RuntimeException(msg)