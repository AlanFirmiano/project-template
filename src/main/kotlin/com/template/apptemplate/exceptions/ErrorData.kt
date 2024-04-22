package com.template.apptemplate.exceptions

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ErrorData(
    val message: String,
    val errors: Set<String>
) {
}