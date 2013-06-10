package seeker

import list.ResumeList

/**
 * Wraps a private ResumeList, exposing only desired behaviors as a trait (currently only to JobSeeker)
 */
trait ResumeListMgmt {

  private val resumeList = new ResumeList()

  def resumesAdd(resume: Resume) = resumeList.add(resume).asInstanceOf[ResumeList]

  def resumesContains(resume: Resume): Boolean = resumeList.contains(resume)

}
