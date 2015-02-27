package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model.{Employer, Job}

class JobService {
  private val postedJobs = new PostedJobs(List())

  def postJob(job:Job) = {
    postedJobs.postJob(job)
  }

  def filterByEmployer(employer:Employer) = {
    postedJobs.filterByEmployer(employer)
  }
}
