package recruiter

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import seeker.{Resume, JobSeeker}
import java.util.{Calendar, GregorianCalendar}

/**
 * Created with IntelliJ IDEA.
 * User: Peter
 * Date: 6/6/13
 * Time: 9:31 AM
 * To change this template use File | Settings | File Templates.
 */
class RecruiterTest extends Specification {

  trait cruders extends Scope {
    val daves = new Recruiter("Five guys all named Dave")
    val andiamo = new Recruiter("Andiamo means 'Suck it Up!' in Italian")
    val now = new GregorianCalendar()
    val tomorrow = new GregorianCalendar()
    tomorrow.add(Calendar.DAY_OF_MONTH, 1)

    val bottleWasherJob = daves.createATSJob("Chief bottle washer")
    val cookJob = daves.createJReqJob("Chief cook")

    def davesPostsAJob(): Recruiter = {
      daves.jobsAdd(daves.createATSJob("Chief bottle washer"))
      return daves
    }

    def davesPostsCookAndBottleWasher(): Recruiter = {
      daves.jobsAdd(bottleWasherJob)
      daves.jobsAdd(cookJob)
      return daves
    }

    def applicantsToCookAndBottleWasher(): Recruiter = {
      davesPostsCookAndBottleWasher()

      val rezzie = new JobSeeker("Rezzie")
      val rezuno = new Resume("Hire me plz!!")
      val rezdos = new Resume("For realz!")
      rezzie.resumesAdd(rezuno)
      rezzie.resumesAdd(rezdos)

      val candie = new JobSeeker("Candie")
      val candiesRessie = new Resume("I feel like a Hundred Grand")
      candie.resumesAdd(candiesRessie)

      candie.applyTo(bottleWasherJob)
      candie.applyTo(cookJob, candiesRessie)

      rezzie.applyTo(bottleWasherJob, rezuno)

      // daves jobs state is now:
      //    bottleWasherJob =applicants=> Candie, Rezzie:"Hire me plz!!"
      //    cookJob =applicant=> Candie:"I feel like a Hundred Grand"

      return daves
    }



  }

  "Recruiter 'Five guys all named Dave'" should {
    "- be displayed by their name when displayed" in new cruders {
      andiamo.toString() must contain("Andiamo means 'Suck it Up!' in Italian")
    }
    "- be able to post jobs" in new cruders {
      davesPostsAJob().jobsCount() mustEqual 1
    }
    "- be able to see a listing of the jobs they have posted" in new cruders {
      davesPostsCookAndBottleWasher().jobsSummarize() must (contain("cook") and contain("bottle washer"))
    }
    "- be able to see applications to their jobs by job" in new cruders {
      applicantsToCookAndBottleWasher().jobsAllApplicationsForJob(bottleWasherJob).toString() must (contain("Rezzie") and contain("Candie"))
    }
    "- be able to see applications to their jobs by date" in new cruders {
      applicantsToCookAndBottleWasher().jobsAllApplicationsForDate(now).toString() must (contain("Rezzie") and contain("Candie"))
    }
    "- (retest jobs by date using wrong date)" in new cruders {
      applicantsToCookAndBottleWasher().jobsAllApplicationsForDate(tomorrow).toString() must not (contain("Rezzie") and contain("Candie"))
    }
    "- be able to see applications to their jobs by job and date" in new cruders {
      applicantsToCookAndBottleWasher().jobsAllApplicationsForJobAndDate(cookJob, now).toString() must contain("Candie")
    }
    "- (retest applications by job and date with wrong date)" in new cruders {
      applicantsToCookAndBottleWasher().jobsAllApplicationsForJobAndDate(cookJob, tomorrow).toString() must not contain("Candie")
    }
  }
  "Jobs" should {
    "- be displayed with a title when displayed" in new cruders {
      bottleWasherJob.toString() must contain("Chief bottle washer")
    }
  }
}
