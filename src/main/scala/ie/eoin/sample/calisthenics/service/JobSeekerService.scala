package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model.{Job, JobSeeker}

class JobSeekerService {

  private val savedJobs = new SavedJobs()

  def saveJob(jobSeeker: JobSeeker, job: Job) = {
    savedJobs.saveJob(jobSeeker, job)
  }

  def getSavedJobs(jobSeeker: JobSeeker) = {
    savedJobs.getSavedJobs(jobSeeker)
  }
}
