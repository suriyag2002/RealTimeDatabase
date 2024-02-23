package com.example.realtimedatabase

/**
 * A data class representing a user.
 *
 * @property firstName The first name of the user. Nullable to allow instances without this information.
 * @property lastName The last name of the user. Nullable for the same reason as firstName.
 * @property age The age of the user. Stored as a String to simplify Firebase interactions, but consider parsing to an Int for age-related logic.
 * @property userName The username. It acts as a unique identifier in this context.
 */
data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val age: String? = null,
    val userName: String? = null
)
