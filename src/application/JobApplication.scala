package application

import seeker.{Resume, JobSeeker}
import job.Job
import java.util.GregorianCalendar

/**
 * In this implementation a JobApplication contains three pieces of information
 *  1. '''jobSeeker''' - A reference to the applicant (JobSeeker)
 *
 *  2. '''resume''' - A reference to the Resume submitted by the JobSeeker
 *
 *  3. '''job''' - A reference to the Job
 *
 *
 * __The JobApplication construct represents a ''many-to-many'' relationship between JobSeekers and Jobs__
 *
 * In order to satisfy this relationship and the OC requirements, ''both'' JobSeeker(s) ''and'' Job(s)
 * maintain references to JobApplications.
 *
 *
 * '''Example 1''' - To look up an applicant for a Job posted by a Recruiter the logic needs to follow
 * the following chain:  '''Recruiter''' -> '''Job''' -> '''JobApplication''' -> '''JobSeeker'''
 *
 * '''Example 2''' - To look up a Job applied to by a JobSeeker, the logic needs to follow this reversed
 * chain: '''JobSeeker''' -> '''JobApplication''' -> '''Job'''
 *
 */
abstract class JobApplication protected (val jobSeeker: JobSeeker, val job: Job, val optionalResume: Option[Resume]) {
  final val applicationDate = new GregorianCalendar()       // TODO: Not internationalized
}

