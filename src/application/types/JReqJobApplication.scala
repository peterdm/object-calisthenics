package application.types

import seeker.{JobSeeker, Resume}
import application.JobApplication
import job.types.JReqJob

/**
 * A JReqJobApplication requires a resume
 */
class JReqJobApplication(jobSeeker: JobSeeker, job: JReqJob, val resume: Resume) extends JobApplication(jobSeeker, job, Some(resume)) {

  override def toString: String = {
    var str = "[JReqJobApplication] "
    str += "Resume: " + resume.content
    str += " ==Job=> " + job.toString
    return str
  }
}