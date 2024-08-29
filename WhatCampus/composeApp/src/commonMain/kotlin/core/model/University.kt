package core.model

data class University(
    val id: Long,
    val name: String,
    val departments: List<Department>,
)

data class Department(
    val id: Long,
    val name: String,
    val homePageUrl: String,
)
