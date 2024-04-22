package com.template.apptemplate.exceptions

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter


@AllArgsConstructor
@Getter
@Setter
class ExceptionMessage (
    val type: String? = null,
    val message: String? = null
)


@AllArgsConstructor
@Getter
@Setter
class FieldNotFoundException : Exception()

@AllArgsConstructor
@Getter
@Setter
class NotFoundException : RuntimeException()

@AllArgsConstructor
@Getter
@Setter
class InvalidFieldMessage(
    val type: String? = null,
    val message: String? = null,
    val data: MutableList<FieldErrorMessage>? = null
)


@Getter
@Setter
@AllArgsConstructor
class FieldErrorMessage(
    val field: String? = null,
    val error: String? = null
)

