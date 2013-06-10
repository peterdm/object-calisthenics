package seeker

import list.JobSeekerList

/**
 * Wraps a private JobSeekerList, exposing only desired behaviors at a trait (currently only to TheLadders)
 */
class JobSeekerListMgmt {

  private val jobSeekerList = new JobSeekerList()

  def jobSeekersAdd(jobSeeker: JobSeeker): JobSeekerList = jobSeekerList.add(jobSeeker).asInstanceOf[JobSeekerList]

  def jobSeekersCount(): Int = jobSeekerList.length

  def jobSeekersFindByName(name: String) = jobSeekerList.findByName(name)
}
