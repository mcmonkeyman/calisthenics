package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model.{Job, JobSeeker}

class SavedJobs {

  var savedJobs: Map[JobSeeker, List[Job]] = Map()

  def saveJob(jobSeeker: JobSeeker, job: Job) = {
    val jobs = savedJobs.get(jobSeeker)

    val newJobs = jobs match {
      case Some(list) => job :: list
      case None => List(job)
    }

    val newMap: Map[JobSeeker, List[Job]] = Map(jobSeeker -> newJobs)
    savedJobs = savedJobs ++ newMap
    savedJobs
  }

  def getSavedJobs(jobSeeker: JobSeeker) = {
    savedJobs.get(jobSeeker)
  }
}
