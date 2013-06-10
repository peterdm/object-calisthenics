package corporate

import java.util.Calendar
import job.Job
import recruiter.{Recruiter, RecruiterListMgmt}
import list.{JobList, JobSeekerList, JobApplicationList}
import seeker.JobSeekerListMgmt

/**
 * In this implementation TheLadders manages two pieces of information
 *  1. '''a JobSeekerList''' (managed via the JobSeekerListMgmt trait)
 *
 *  2. '''a RecruiterList''' (managed via the RecruiterListMgmt trait)
 *
 *
 * Using these two constructs, TheLadders can report on job posting and application activities
 */
class TheLadders extends JobSeekerListMgmt with RecruiterListMgmt {

  def countJobApplicationsForJob(job: Job): Int = job.applicationCount()

  def countJobApplicationsForRecruiter(recruiter: Recruiter): Int = recruiter.jobsAllApplicationsCount()

  def jobApplicationsForDate(date: Calendar): JobApplicationList = recruitersAllJobApplicationsForDate(date)

  def jobsFindByTitle(title: String): JobList = recruitersAllJobsForTitle(title)

  def distinctJobApplicantsForDate(date: Calendar): JobSeekerList = {
    val applicationsForDate = jobApplicationsForDate(date)
    val applicants = applicationsForDate.applicants()

    return applicants.distinct()
  }

}
