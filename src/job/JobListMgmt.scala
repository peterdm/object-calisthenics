package job

import list.{JobApplicationList, JobList}
import java.util.Calendar
import common.UnauthorizedException

/**
 * Wraps a private JobList, exposing only desired behaviors as a trait (to JobSeeker and Recruiter)
 *
 * Note:  Start here to discover the fatal flaw in this design.  (Hint:  It's an unauthorized access vector)
 * I'll buy a beer for the first person to write a test exposing it.
 */

trait JobListMgmt {

  protected val jobList = new JobList()

  def jobsAdd(job: Job): JobList = jobList.add(job).asInstanceOf[JobList]

  def jobsContains(job: Job): Boolean = jobList.contains(job)

  def jobsCount(): Int = jobList.length

  def jobsFindByTitle(title: String): JobList = jobList.findByTitle(title)

  def jobsSummarize(): String = jobList.toString()
}
