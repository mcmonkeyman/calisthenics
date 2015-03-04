package ie.eoin.sample.calisthenics.model.collections

import ie.eoin.sample.calisthenics.model.{Job, JobSeeker}

class SavedJobs(val savedJobs: Map[JobSeeker, List[Job]]) {

  def this() = this(Map())

  def saveJob(jobSeeker: JobSeeker, job: Job) = {
    val newJobs = job :: getOldJobs(jobSeeker)
    val newMap: Map[JobSeeker, List[Job]] = Map(jobSeeker -> newJobs)
    new SavedJobs(savedJobs ++ newMap)
  }

  private def getOldJobs(jobSeeker:JobSeeker) = {
    savedJobs.get(jobSeeker).getOrElse(List())
  }

  def getSavedJobs(jobSeeker: JobSeeker) = {
    savedJobs.get(jobSeeker)
  }

  def size = savedJobs.size
}
