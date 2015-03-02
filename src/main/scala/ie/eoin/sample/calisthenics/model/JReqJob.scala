package ie.eoin.sample.calisthenics.model

class JReqJob(override val poster: Person) extends Job(poster) {
  override def requiresResume = true
}
