package job

import list.JobSeekerList
import application.JobApplicationListMgmt

/**
 * In this implementation, a Job is a container for two pieces of information
 *  1. '''title''' - The job title (clearly there can ultimately be more Job metadata)
 *
 *  2. '''a JobApplicationList''' (managed via the JobApplicationListMgmt trait)
 *
 *
 * When JobSeekers apply to this job, their JobApplications are added to the Job's JobApplicationList
 * (using methods defined in the JobApplicationListMgmt trait)
 */
abstract class Job protected (val title: String) extends JobApplicationListMgmt {

  def applicantList(): JobSeekerList = applicationsApplicantList()

  def summarize(): String

  override def toString(): String = summarize()
}
