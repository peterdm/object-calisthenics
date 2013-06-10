package list

import application.JobApplication
import job.Job
import java.util.Calendar
import common.DateUtils

/**
 * A list of JobApplications
 */
class JobApplicationList extends CommonList[JobApplication] {
  def this(traversable: Traversable[JobApplication]) = {    // Why do I have to redefine this?
    this()
    traversable.copyToBuffer(items)
  }

  def findByJob(job: Job): JobApplicationList =
    new JobApplicationList(filter(ja => (ja.job == job)))


  def findByDate(date: Calendar): JobApplicationList = {
    val datePart = DateUtils.thisDatePart(date)
    val nextDatePart = DateUtils.nextDatePart(date)
    new JobApplicationList(
      filter(ja =>
        (ja.applicationDate.after(datePart) && ja.applicationDate.before(nextDatePart))))
  }

  def findByJobAndDate(job: Job, date: Calendar): JobApplicationList =  {
    val datePart = DateUtils.thisDatePart(date)
    val nextDatePart = DateUtils.nextDatePart(date)
    new JobApplicationList(
      filter(ja =>
        (ja.job == job && ja.applicationDate.after(datePart) && ja.applicationDate.before(nextDatePart))))
  }

  def applicants(): JobSeekerList = new JobSeekerList(items.map(ja => ja.jobSeeker))
}