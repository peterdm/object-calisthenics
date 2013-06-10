package application

import list.{JobSeekerList, JobApplicationList}
import job.Job
import java.util.Calendar

/**
 * Wraps a private JobApplicationList, exposing only desired behaviors as a trait (currently to JobSeeker and Job)
 *
 *
 */
trait JobApplicationListMgmt {

  private val applications = new JobApplicationList()

  def applicationsAdd(app: JobApplication) = applications.add(app).asInstanceOf[JobApplicationList]

  def applicationsAll(): JobApplicationList = applications

  def applicationCount(): Int = applications.length

  def applicationsFindByJob(job: Job): JobApplicationList = applications.findByJob(job)

  def applicationsFindByDate(date: Calendar): JobApplicationList = applications.findByDate(date)

  def applicationsFindByJobAndDate(job: Job, date: Calendar): JobApplicationList = applications.findByJobAndDate(job, date)

  def applicationsApplicantList(): JobSeekerList =  applications.applicants()

  def applicationsSummarize(): String = applications.toString()

}
