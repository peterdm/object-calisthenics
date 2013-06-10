package job.types

import seeker.{Resume, JobSeeker}
import job.Job
import application.types.JReqJobApplication

/**
 * A JReqJob can only receive applications with a resume
 */
class JReqJob(title: String) extends Job(title) {

  def applyTo(jobSeeker: JobSeeker, resume: Resume): JReqJobApplication = {
    val app = new JReqJobApplication(jobSeeker, this, resume)
    applicationsAdd(app)

    return app
  }

  def summarize(): String = {
    return "[JReqJob] title: "+title+", applicants: ("+applicantList().toString()+")"
  }
}
