package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model.{Job, JobSeeker}
import ie.eoin.sample.calisthenics.model.collections.SavedJobs

class JobSeekerService {

  private var savedJobs = new SavedJobs()

  def saveJob(jobSeeker: JobSeeker, job: Job) = {
    savedJobs = savedJobs.saveJob(jobSeeker, job)
    savedJobs
  }

  def getSavedJobs(jobSeeker: JobSeeker) = {
    savedJobs.getSavedJobs(jobSeeker)
  }
}
