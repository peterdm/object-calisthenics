package job.types

import job.Job
import seeker.{Resume, JobSeeker}
import application.types.ATSJobApplication

/**
 * An ATSJob can receive applications with or without a resume
 * using the two overloaded applyTo methods
 */
class ATSJob(title: String) extends Job(title) {

  def applyTo(jobSeeker: JobSeeker): ATSJobApplication = {
    val app = new ATSJobApplication(jobSeeker, this)
    applicationsAdd(app)

    return app
  }

  def applyTo(jobSeeker: JobSeeker, resume: Resume): ATSJobApplication = {
    val app = new ATSJobApplication(jobSeeker, this, resume)
    applicationsAdd(app)

    return app
  }

  def summarize(): String = {
    return "[ATSJob] title: "+title+", applicants: ("+applicantList().toString()+")"
  }
}
