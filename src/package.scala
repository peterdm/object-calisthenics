/**
 * The Application package contains classes which model [[application.JobApplication]] data and behaviors
 *
 * [[application.JobApplication]] is the abstract parent class of [[application.types.ATSJobApplication]]
 * and [[application.types.JReqJobApplication]].  It is abstract b/c only the child classes should be instantiated
 * (since there are different requirements between the two types of JobApplications.
 *
 * The [[application.JobApplicationListMgmt]] mixin exposes behaviors for managing lists of
 * JobApplications (used by JobSeeker actor and Job container)
 *
 * [[application.InvalidJobApplicationException]] is thrown as a guard against JobSeekers applying to Jobs
 * with resumes __not__ present in their ResumeList.  As long as the UI enforces this restriction, this exception
 * should never be thrown.
 */
package object application {}

/**
 * The common package is a catchall.
 */
package object common {}

/**
 * The corporate package contains only [[corporate.TheLadders]].  This is the actor in use to demonstrate
 * systemwide access across all [[recruiter.Recruiter]]s, their [[job.Job]]s and [[seeker.JobSeeker]]s
 */
package object corporate {}

/**
 * The job package contains classes which model [[job.Job]] data and behaviors
 *
 * [[job.Job]] is the abstract parent class of [[job.types.ATSJob]]
 * and [[job.types.JReqJob]].  It is abstract b/c only the child classes should be instantiated
 * (as each job type may only accept JobApplications of the corresponding type.)
 *
 * The [[job.JobListMgmt]] mixin exposes behaviors for managing lists of JobApplications
 * (used by the JobSeeker actor and derivatively -- via RecruiterJobListMgmt by the Recruiter actor)
 */
package object job {}

/**
 * The list package centralizes the container classes used in this application
 * [[list.JobApplicationList]], [[list.JobList]], [[list.JobSeekerList]],
 * [[list.RecruiterList]], and [[list.ResumeList]] all extend [[list.CommonList]] for
 * their respective types. (Exposing common list behaviors (from CommonList) where needed.)
 *
 * Note:  The ArrayBuffer may or may not be the ideal collection class to underpin these list classes.
 * However, the general use-case is the same, and by centralizing this implementation choice, future changes
 * to this collection implementation become trivial.
 */
package object list {}

/**
 * The recruiter package contains classes which model data and behaviors of the [[recruiter.Recruiter]] actor
 *
 * The [[recruiter.RecruiterListMgmt]] mixin exposes behaviors for managing lists of Recruiters
 * (used only by the [[corporate.TheLadders]] actor at present)
 */
package object recruiter {}

/**
 * The seeker package contains classes which model data and behaviors of the [[seeker.JobSeeker]] actor
 * and their [[seeker.Resume]]s
 *
 * The [[seeker.JobSeekerListMgmt]] mixin exposes behaviors for managing lists of JobSeekers
 * (used only by the [[corporate.TheLadders]] actor at present)
 *
 * The [[seeker.ResumeListMgmt]] mixin exposes behaviors for managing lists of Resumes
 * (used only by the [[seeker.JobSeeker]] actor at present)
 */
package object seeker {}


