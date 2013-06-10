package list

import job.Job

/**
 * A list of Jobs
 */
class JobList extends CommonList[Job] {
  def this(traversable: Traversable[Job]) = {    // Why do I have to redefine this?
    this()
    traversable.copyToBuffer(items)
  }

  def findByTitle(text: String): JobList =
    new JobList(filter(j => j.title.contains(text)))

}