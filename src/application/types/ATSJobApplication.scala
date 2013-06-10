package application.types

import seeker.{JobSeeker, Resume}
import application.JobApplication
import job.types.ATSJob

/**
 * An ATSJobApplication can be created with or without a resume
 */
class ATSJobApplication private (jobSeeker: JobSeeker, job: ATSJob, optionalResume: Option[Resume]) extends JobApplication(jobSeeker, job, optionalResume) {
  def this(jobSeeker: JobSeeker, job: ATSJob, resume: Resume) = this(jobSeeker, job, Some(resume))
  def this(jobSeeker: JobSeeker, job: ATSJob) = this(jobSeeker, job, None)

  override def toString(): String = {
    var str = "[ATSJobApplication] "
    str += jobSeeker.summarize()
    optionalResume match {
      case Some(r) => str += "Resume: " + r.content
      case None => str += "<No Resume>"
    }
    str += " ==Job=> " + job.summarize()

    return str
  }
}

