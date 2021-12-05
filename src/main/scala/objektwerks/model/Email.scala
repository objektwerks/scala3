package objektwerks.model

final case class Email(id : String,
                       license: String,
                       address: String,
                       processed: Boolean = false,
                       valid: Boolean = false)
