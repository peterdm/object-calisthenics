package job

import list.JobApplicationList
import common.UnauthorizedException
import java.util.Calendar

/**
 * Exposes additional JobList functionality for Recruiters
 */
trait RecruiterJobListMgmt extends JobListMgmt {

  def jobsAllApplications(): JobApplicationList = {
    val allApplications = new JobApplicationList()

    for (job: Job <- jobList) {
      allApplications.addAll(job.applicationsAll())
    }

    return allApplications
  }

  def jobsAllApplicationsCount(): Int = this.jobsAllApplications().length

  def jobsAllApplicationsForJob(job: Job): JobApplicationList = {
    if (!jobsContains(job))
      throw new UnauthorizedException("Job not found in this list of jobs")

    return job.applicationsAll()
  }

  def jobsAllApplicationsForDate(date: Calendar): JobApplicationList = {
    val applicationsForDate = new JobApplicationList()

    for (job: Job <- jobList) {
      applicationsForDate.addAll(job.applicationsFindByDate(date))
    }

    return applicationsForDate
  }

  def jobsAllApplicationsForJobAndDate(job: Job, date: Calendar): JobApplicationList = {
    if (!jobsContains(job))
      throw new UnauthorizedException("Job not found in this list of jobs")

    return job.applicationsFindByDate(date)
  }


}
