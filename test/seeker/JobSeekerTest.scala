package seeker

/**
 * Created with IntelliJ IDEA.
 * User: Peter
 * Date: 6/4/13
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */

import application.InvalidJobApplicationException
import job.types.{JReqJob, ATSJob}
import list.JobList
import org.specs2.mutable._
import org.specs2.specification.Scope
import recruiter.Recruiter


class JobSeekerTest extends Specification {


  trait seekerz extends Scope {

    val rezzie = new JobSeeker("Rezzie")
    val rezuno = new Resume("Hire me plz!!")
    val rezdos = new Resume("For realz!")
    rezzie.resumesAdd(rezuno)
    rezzie.resumesAdd(rezdos)

    val candie = new JobSeeker("Candie")
    val candiesRessie = new Resume("I feel like a Hundred Grand")
    candie.resumesAdd(candiesRessie)

    val postie = new JobSeeker("Postie")

    val jobuno = new ATSJob("Pizzaria Uno")
    val jobdos = new ATSJob("Dos Equis")
    val atstres = new ATSJob("Tres Leches")
    val jobcuatro = new ATSJob("Quattro Frommagio")

    val jreqcinco = new JReqJob("SendYurRessie By El Cinco de Mayo")

    val timpostr = new JobSeeker("Tim Postr")
    val otherRessie = new Resume("My name is Jim Foster!")

    def testSaveRetrieveJob(): JobList = {
      postie.jobsAdd(jobcuatro)
      postie.jobsFindByTitle("Quattro")
    }

    def candieAppliedToJobUno(): JobSeeker = {
      candie.applyTo(jobuno, candiesRessie)
      return candie
    }

    def timpostrApplyingToJobDosWithBadResume() = timpostr.applyTo(jobdos, otherRessie)

    def candieAppliedToJReqCincoWithResume(): JobSeeker = {
      candie.applyTo(jreqcinco, candiesRessie)
      return candie
    }
    def candieAppliedToATSTresWithResume(): JobSeeker = {
      candie.applyTo(atstres, candiesRessie)
      return candie
    }
    def candieAppliedToATSTresWithoutResume(): JobSeeker = {
      candie.applyTo(atstres)
      return candie
    }

    def rezzieAppliedToJReqCincoAndATSTresWithDifferentResumes(): JobSeeker = {
      rezzie.applyTo(jreqcinco, rezuno)
      rezzie.applyTo(atstres, rezdos)
      return rezzie
    }

    def rezzieWithSavedJobs(): JobSeeker = {
      rezzie.jobsAdd(jobuno)
      rezzie.jobsAdd(jobdos)
      rezzie.jobsAdd(atstres)
      rezzie.jobsAdd(jobcuatro)
      rezzie.jobsAdd(jreqcinco)

      return rezzie
    }

  }


  "JobSeeker 'Postie'" should {
    "- be displayed by their name when displayed" in new seekerz {
      postie.toString() must contain("Postie")
    }
    "- be able to save a job for later viewing" in new seekerz {
      testSaveRetrieveJob().length mustEqual 1
    }
  }
  "JobSeeker 'Candie'" should {
    "- be able to apply to a job posted by a recruiter" in new seekerz {
      candieAppliedToJobUno().applicationsFindByJob(jobuno).length mustEqual 1
    }
    "- be able to apply to a JReq job with a resume" in new seekerz {
      candieAppliedToJReqCincoWithResume().applicationsFindByJob(jreqcinco).length mustEqual 1
    }
    "- be able to apply to an ATS job with a resume" in new seekerz {
      candieAppliedToATSTresWithResume().applicationsFindByJob(atstres).length mustEqual 1
    }
    "- be able to apply to an ATS job WITHOUT a resume" in new seekerz {
      candieAppliedToATSTresWithoutResume().applicationsFindByJob(atstres).length mustEqual 1
    }
    "- there is no code path to apply to a JReq job without a resume -- generates compile-time error" in new seekerz {
      1 mustEqual 1
    }
  }
  "JobSeeker 'Tim Postr'" should {
    "- fail to apply to a job with a resume that's not his" in new seekerz {
      timpostrApplyingToJobDosWithBadResume() must throwA[InvalidJobApplicationException]
    }
  }
  "JobSeeker 'Rezzie'" should {
    "- be able to apply to different jobs with different resumes" in new seekerz {
      rezzieAppliedToJReqCincoAndATSTresWithDifferentResumes().applicationCount() mustEqual 2
    }
    "- be able to apply to see a listing of jobs saved for later viewing" in new seekerz {
      rezzieWithSavedJobs().jobsSummarize() must (contain("Uno") and contain("Dos") and contain("Tres") and contain("Quattro"))
    }
    "- be able to see a listing of the jobs for which they have applied" in new seekerz {
      rezzieAppliedToJReqCincoAndATSTresWithDifferentResumes().applicationsSummarize() must (contain("Cinco") and contain("Tres"))
    }

  }



}
