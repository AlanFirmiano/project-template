package com.template.apptemplate.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "template_model")
data class TemplateModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @field:NotNull(message = "field not null")
        var nome: String? = null,

        @field:NotNull(message = "field not null")
        @field:Email(message = "email not valid")
        var email: String? = null,
        var endereco: String? = null
) {
        constructor() : this(0, "", "", "")
}