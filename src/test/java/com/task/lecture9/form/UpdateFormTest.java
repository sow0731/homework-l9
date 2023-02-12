package com.task.lecture9.form;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


class UpdateFormTest {

    @Test
    void Vinylデータが全て正しく入力されバリデーションエラーとならないこと() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        UpdateForm form = new UpdateForm("aa", "bb", "cc", "2000");

        Set<ConstraintViolation<UpdateForm>> violations = validator.validate(form);
        assertThat(violations.size()).isEqualTo(0);
    }
    @Test
    void 各項目にnullを入力した際にバリデーションエラーとならないこと() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        UpdateForm form = new UpdateForm(null, null, null, null);

        Set<ConstraintViolation<UpdateForm>> violations = validator.validate(form);
        assertThat(violations.size()).isEqualTo(0);

    }
    @Test
    void 各項目に空文字を入力した際にreleaseYearのみバリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        UpdateForm form = new UpdateForm("", "", "", "");

        Set<ConstraintViolation<UpdateForm>> result = validator.validate(form);
        assertThat(result.size()).isEqualTo(1);

        assertThat(result).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(tuple("releaseYear", "整数4桁で入力してください"));
    }
    @Test
    void 各項目に制限文字数以上で入力した際にバリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        UpdateForm form = new UpdateForm(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccc",
                "2000005");

        Set<ConstraintViolation<UpdateForm>> result = validator.validate(form);
        assertThat(result.size()).isEqualTo(4);

        assertThat(result).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(
                        tuple("title", "titleは50文字以内で入力してください"),
                        tuple("artist", "artistは50文字以内で入力してください"),
                        tuple("label", "labelは50文字以内で入力してください"),
                        tuple("releaseYear", "整数4桁で入力してください"));
    }

    @Test
    void releaseYearに整数以外が入力された場合バリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        UpdateForm form = new UpdateForm("aa", "bb", "cc", "dddd");

        Set<ConstraintViolation<UpdateForm>> result = validator.validate(form);
        assertThat(result.size()).isEqualTo(1);

        assertThat(result).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(tuple("releaseYear", "整数4桁で入力してください"));
    }

    @Test
    void releaseYearに4桁未満の整数が入力された場合バリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        UpdateForm form = new UpdateForm("aa", "bb", "cc", "201");

        Set<ConstraintViolation<UpdateForm>> result = validator.validate(form);
        assertThat(result.size()).isEqualTo(1);

        assertThat(result).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(tuple("releaseYear", "整数4桁で入力してください"));
    }
}
