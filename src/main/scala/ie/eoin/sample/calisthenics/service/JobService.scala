package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model._

class JobService {

  private var postedJobs = new PostedJobs()

  def postJob(job: Job) = {
    postedJobs = postedJobs.postJob(job)
    postedJobs
  }

  def filterByEmployer(employer: Employer) = {
    postedJobs.filterByEmployer(employer)
  }
}
