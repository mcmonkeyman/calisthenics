package ie.eoin.sample.calisthenics.model

class JobSeeker(override val name: PersonName, resumes: Seq[Resume] = List()) extends Person(name) {
  def containsResume(resumeToCheck: Option[Resume]) = resumeToCheck.isDefined && resumes.contains(resumeToCheck.get)
}
