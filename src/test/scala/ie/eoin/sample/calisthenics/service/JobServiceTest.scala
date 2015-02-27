package ie.eoin.sample.calisthenics.service

import junit.framework.{TestCase}
import junit.framework.Assert._

import ie.eoin.sample.calisthenics.model.{Employer, PersonName, Person, Job}

class JobServiceTest extends TestCase("jobservice") {


  override def setUp() = {

  }

  def testPostJob() = {
    val jobService = new JobService()
    val testJob1 = new Job(new Person(new PersonName("testRecruiter")))
    val results = jobService.postJob(testJob1)

    assertEquals(results.size, 1)
  }


  def testFilteredByEmployer() = {
    val employer1 = new Employer(new PersonName("employer1"))
    val employer2 = new Employer(new PersonName("employer2"))
    val jobService = new JobService()
    val testJob1 = new Job(employer1)

    jobService.postJob(testJob1)
    val results1 = jobService.filterByEmployer(employer1)
    val results2 = jobService.filterByEmployer(employer2)

    assertEquals(results1.size, 1)
    assertEquals(results2.size, 0)
  }


}
