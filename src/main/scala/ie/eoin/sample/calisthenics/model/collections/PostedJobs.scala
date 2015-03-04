package ie.eoin.sample.calisthenics.model.collections

import ie.eoin.sample.calisthenics.model.{Employer, Job}

class PostedJobs(val jobs: List[Job]) {

  def this() = this(List())

  def postJob(job: Job) = {
    new PostedJobs(job :: jobs)
  }

  def filterByEmployer(employer: Employer) = {
    val results = jobs.filter {
      job: Job => job.isOwner(employer)
    }
    new PostedJobs(results)
  }

  def size = jobs.size
}
