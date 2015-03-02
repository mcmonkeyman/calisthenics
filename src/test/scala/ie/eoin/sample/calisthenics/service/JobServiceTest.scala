package ie.eoin.sample.calisthenics.service

import org.scalatest.junit.AssertionsForJUnit
import ie.eoin.sample.calisthenics.model._
import org.junit.{Assert, Test}


class JobServiceTest extends AssertionsForJUnit {

  @Test
  def testPostJob() = {
    val jobService = new JobService()
    val testJob1 = new Job(new Person(new PersonName("testRecruiter")))
    val results = jobService.postJob(testJob1)

    Assert.assertEquals(results.size, 1)
  }

  @Test
  def testFilteredByEmployer() = {
    val employer1 = new Employer(new PersonName("employer1"))
    //    val employer1: Employer = testData.get("employer1")
    val employer2 = new Employer(new PersonName("employer2"))
    val jobService = new JobService()
    val testJob1 = new Job(employer1)

    jobService.postJob(testJob1)
    val results1 = jobService.filterByEmployer(employer1)
    val results2 = jobService.filterByEmployer(employer2)

    Assert.assertEquals(results1.size, 1)
    Assert.assertEquals(results2.size, 0)
  }

}
