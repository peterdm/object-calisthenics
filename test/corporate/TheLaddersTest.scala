package corporate

import org.specs2.specification.Scope
import org.specs2.mutable.Specification
import recruiter.Recruiter
import seeker.JobSeeker
import java.util.GregorianCalendar


/**
 * Created with IntelliJ IDEA.
 * User: Peter
 * Date: 6/7/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
class TheLaddersTest extends Specification {

  trait jobz extends Scope {

    def thandysJobs(): Recruiter = {
      val tlc = new TheLadders
      val thandy = new Recruiter("Thandy Thinskin")
      tlc.recruitersAdd(thandy)

      thandy.jobsAdd(thandy.createATSJob("Jeopardy Host"))
      thandy.jobsAdd(thandy.createJReqJob("Jeopardy Host"))

      return thandy
    }

    def doubleDanny(): TheLadders = {
      val tlc = new TheLadders
      tlc.jobSeekersAdd(new JobSeeker("Danny Dubious"))
      tlc.jobSeekersAdd(new JobSeeker("Danny Dubious"))

      tlc.jobSeekersAdd(new JobSeeker("Franny Frivolous"))

      return tlc
    }

    def tripleThandy(): TheLadders = {

      val tlc = new TheLadders
      tlc.recruitersAdd(new Recruiter("Thandy Thinskin"))
      tlc.recruitersAdd(new Recruiter("Thandy Thinskin"))
      tlc.recruitersAdd(new Recruiter("Thandy Thinskin"))

      tlc.recruitersAdd(new Recruiter("Randy Ruskin"))

      return tlc
    }

  }

  trait ladderz extends Scope {

    val tlc = new TheLadders

    val thandy = new Recruiter("Thandy Thinskin")
    tlc.recruitersAdd(thandy)

    val sodajerk = thandy.createATSJob("Soda Jerk")
    val spritzer = thandy.createATSJob("Perfume Spritzer")
    val urchin = thandy.createATSJob("Street Urchin")

    thandy.jobsAdd(sodajerk)
    thandy.jobsAdd(spritzer)
    thandy.jobsAdd(urchin)


    def jobSeekersToday(): TheLadders = {

      val danny = new JobSeeker("Danny Dubious")
      val franny = new JobSeeker("Franny Frivolous")
      tlc.jobSeekersAdd(danny)
      tlc.jobSeekersAdd(franny)

      danny.applyTo(sodajerk)
      danny.applyTo(spritzer)
      franny.applyTo(spritzer)
      franny.applyTo(urchin)

      return tlc
    }

  }


  "TheLadders" should {
    "- be able to see jobseekers who have applied to jobs on any given day" in new ladderz {
      jobSeekersToday().distinctJobApplicantsForDate(new GregorianCalendar()).length mustEqual 2
    }
    "- be able to see aggregate job application numbers by job" in new ladderz {
      jobSeekersToday().countJobApplicationsForJob(spritzer) mustEqual 2
    }
    "- be able to see aggregate job application numbers by recruiter" in new ladderz {
      jobSeekersToday().countJobApplicationsForRecruiter(thandy) mustEqual 4
    }
  }
  "The system" should {
    "- handle more than one job with the same title" in new jobz {
      thandysJobs().jobsFindByTitle("Jeopardy Host").length mustEqual 2
    }
    "- handle more than one jobseeker with the same name" in new jobz {
      doubleDanny().jobSeekersFindByName("Danny Dubious").length mustEqual 2
    }
    "- handle more than one recruiter with the same name" in new jobz {
      tripleThandy().recruitersFindByName("Thandy Thinskin").length mustEqual 3
    }
  }
}
