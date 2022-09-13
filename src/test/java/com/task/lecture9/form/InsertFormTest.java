package com.task.lecture9.form;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InsertFormTest {

    @Test
    void Vinylデータが全て正しく入力されバリデーションエラーとならないこと() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        InsertForm form = new InsertForm("aa", "bb", "cc", "2000");

        Set<ConstraintViolation<InsertForm>> violations = validator.validate(form);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void 必須項目が空白の場合バリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        InsertForm form = new InsertForm("", "", "", "");

        Set<ConstraintViolation<InsertForm>> result = validator.validate(form);
        assertThat(result.size()).isEqualTo(5);

        ConstraintViolation<InsertForm> violation = result.iterator().next();
        assertThat(violation).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(
                        tuple("title", "titleを入力をしてください"),
                        tuple("artist", "artistを入力をしてください"),
                        tuple("label", "labelを入力をしてください"),
                        tuple("releaseYear", "releaseYearを入力してください"),
                        tuple("releaseYear", "releaseYearは4桁で入力してください")
                             );
    }
}