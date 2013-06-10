package application

/**
 * Thrown when something is amiss with a job application attempt
 *  - e.g. Applicant submits a resume that isn't one of theirs
 */
class InvalidJobApplicationException(msg: String) extends RuntimeException(msg)
