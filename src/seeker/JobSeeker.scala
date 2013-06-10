package seeker

import application.{JobApplicationListMgmt, InvalidJobApplicationException}
import job.JobListMgmt
import job.types.{ATSJob, JReqJob}
import application.types.{ATSJobApplication, JReqJobApplication}
import common.Person

/**
 * In this implementation a JobSeeker manages four pieces of information
 *  1. '''name''' - The JobSeeker's name (clearly more information can ultimately be added to a JobSeeker)
 *
 *  2. '''a JobApplicationList''' (managed via the JobApplicationListMgmt trait)
 *
 *  3. '''a ResumeList''' (managed via the ResumeListMgmt trait)
 *
 *  4. '''a JobList''' (for saved jobs - managed via the JobListMgmt trait)
 *
 *
 * The JobSeeker is one of the three actors in this system (JobSeeker, Recruiter, TheLadders)
 */
class JobSeeker(name: String) extends Person(name) with JobListMgmt with JobApplicationListMgmt with ResumeListMgmt {       // Traits:  ResumeManager, JobListManager (reused by Recruiter+TLC), JobApplicationManager (reused by Job+TLC)?

  def applyTo(job: JReqJob, resume: Resume): JReqJobApplication = {
    if (!resumesContains(resume))
      throw new InvalidJobApplicationException("Unknown resume submitted")

    val app = job.applyTo(this, resume)
    applicationsAdd(app)

    return app
  }

  def applyTo(job: ATSJob, resume: Resume): ATSJobApplication = {
    if (!resumesContains(resume))
      throw new InvalidJobApplicationException("Unknown resume submitted")

    val app = job.applyTo(this, resume)
    applicationsAdd(app)

    return app
  }

  def applyTo(job: ATSJob): ATSJobApplication = {
    val app = job.applyTo(this)
    applicationsAdd(app)

    return app
  }

  def summarize(): String = {
    "** JobSeeker: " + name + " **"
  }

  override def toString(): String = name

}
