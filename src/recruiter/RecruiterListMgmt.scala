package recruiter

import list.{JobList, RecruiterList, JobApplicationList}
import java.util.Calendar

/**
 * Wraps a private RecruiterList, exposing only desired behaviors as a trait (to TheLadders)
 */
trait RecruiterListMgmt {
  private val recruiterList = new RecruiterList()

  def recruitersAdd(recruiter: Recruiter): RecruiterList = recruiterList.add(recruiter).asInstanceOf[RecruiterList]

  def recruitersAllJobApplicationsForDate(date: Calendar): JobApplicationList = {
    val allApplicationsForDate = new JobApplicationList()

    for (recruiter: Recruiter <- recruiterList) {
      allApplicationsForDate.addAll(recruiter.jobsAllApplicationsForDate(date))
    }

    return allApplicationsForDate
  }

  def recruitersAllJobsForTitle(title: String): JobList = {
    val allJobsForTitle = new JobList()

    for (recruiter: Recruiter <- recruiterList) {
      allJobsForTitle.addAll(recruiter.jobsFindByTitle(title))
    }

    return allJobsForTitle
  }

  def recruitersCount(): Int = recruiterList.length

  def recruitersFindByName(name: String) = recruiterList.findByName(name)

}
