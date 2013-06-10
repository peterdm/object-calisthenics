package list

import seeker.JobSeeker

/**
 * A list of JobSeekers
 */
class JobSeekerList extends CommonList[JobSeeker] {
  def this(traversable: Traversable[JobSeeker]) = {    // Why do I have to redefine this?
    this()
    traversable.copyToBuffer(items)
  }

  def distinct(): JobSeekerList = new JobSeekerList(items.distinct)

  def findByName(name: String): JobSeekerList = new JobSeekerList(filter(r => r.name == name))
}
