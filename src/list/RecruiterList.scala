package list

import recruiter.Recruiter

/**
 * A list of Recruiters
 */

class RecruiterList extends CommonList[Recruiter]  {
  def this(traversable: Traversable[Recruiter]) = {    // Why do I have to redefine this?
    this()
    traversable.copyToBuffer(items)
  }

  def findByName(name: String): RecruiterList = new RecruiterList(filter(r => r.name == name))
}