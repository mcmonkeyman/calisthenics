package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model.{Employer, Job}

class PostedJobs(var jobs: List[Job]) {

  def postJob(job: Job) = {
    jobs = job :: jobs
    jobs
  }

  def filterByEmployer(employer: Employer) = {
    jobs.filter {
      job: Job => job.isOwner(employer)
    }
  }
}
