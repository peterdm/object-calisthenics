package common

import java.util.Calendar

/**
 * Can't avoid'em
 */
object DateUtils {

  def thisDatePart(date: Calendar): Calendar = {
    val datePart: Calendar = date.clone().asInstanceOf[Calendar]
    datePart.set(Calendar.HOUR_OF_DAY, 0)
    datePart.set(Calendar.MINUTE, 0)
    datePart.set(Calendar.SECOND, 0)
    datePart.set(Calendar.MILLISECOND, 0)

    return datePart
  }

  def nextDatePart(date: Calendar): Calendar = {
    val datePart = thisDatePart(date)
    datePart.add(Calendar.DAY_OF_MONTH, 1)

    return datePart
  }

}
