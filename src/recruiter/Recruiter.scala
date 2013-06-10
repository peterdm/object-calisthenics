package recruiter

import common.Person
import job.RecruiterJobListMgmt
import job.types.{JReqJob, ATSJob}

/**
 * In this implementation a Recruiter manages two pieces of information
 *  1. '''name''' - The Recruiter's name
 *
 *  2. '''a JobList''' (of Jobs posted by this Recruiter)
 *
 *
 * The Recruiter is one of the three actors in this system (JobSeeker, Recruiter, TheLadders)
 *
 */
class Recruiter(name: String) extends Person(name) with RecruiterJobListMgmt {

  def createATSJob(jobName: String): ATSJob = new ATSJob(jobName)
  def createJReqJob(jobName: String): JReqJob = new JReqJob(jobName)

  override def toString: String = {
    "** Recruiter: " + name + " **"
  }

}
