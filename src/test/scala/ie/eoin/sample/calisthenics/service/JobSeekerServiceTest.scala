package ie.eoin.sample.calisthenics.service

import ie.eoin.sample.calisthenics.model.{Employer, Job, PersonName, JobSeeker}
import org.junit.{Assert, Test}
import org.scalatest.junit.AssertionsForJUnit

class JobSeekerServiceTest extends AssertionsForJUnit {

  @Test
  def testSaveJob() = {
    val jobSeekerService = new JobSeekerService()
    val jobSeeker1 = new JobSeeker(new PersonName("jobSeeker1"))
    val jobSeeker2 = new JobSeeker(new PersonName("jobSeeker2"))
    val employer1 = new Employer(new PersonName("employer1"))
    val testJob1 = new Job(employer1)

    jobSeekerService.saveJob(jobSeeker1, testJob1)

    val result = jobSeekerService.getSavedJobs(jobSeeker1)
    Assert.assertEquals(result.get.size, 1)

    val result2 = jobSeekerService.getSavedJobs(jobSeeker2)
    Assert.assertEquals(result2, None)
  }
}
