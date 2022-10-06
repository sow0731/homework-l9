package com.task.lecture9.form;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class InsertFormTest {

    @Test
    void Vinylデータが全て正しく入力されバリデーションエラーとならないこと() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        InsertForm form = new InsertForm("aa", "bb", "cc", "2000");

        Set<ConstraintViolation<InsertForm>> violations = validator.validate(form, ValidationGroupAll.class);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    void 必須項目が空白の場合バリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        InsertForm form = new InsertForm("", "", "", "");

        Set<ConstraintViolation<InsertForm>> result = validator.validate(form, ValidationGroupAll.class);
        assertThat(result.size()).isEqualTo(4);

        assertThat(result).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(
                        tuple("title", "titleを入力してください"),
                        tuple("artist", "artistを入力してください"),
                        tuple("label", "labelを入力してください"),
                        tuple("releaseYear", "releaseYearを入力してください"));
    }

    @Test
    void 必須項目を制限以上の文字数で入力した場合バリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        InsertForm form = new InsertForm(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                "ccccccccccccccccccccccccccccccccccccccccccccccccccc",
                "20003");

        Set<ConstraintViolation<InsertForm>> result = validator.validate(form, ValidationGroupAll.class);
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
        InsertForm form = new InsertForm(
                "aaaaa",
                "bbbb",
                "cccccc",
                "eeee");

        Set<ConstraintViolation<InsertForm>> result = validator.validate(form, ValidationGroupAll.class);
        assertThat(result.size()).isEqualTo(1);

        assertThat(result).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(
                        tuple("releaseYear", "整数4桁で入力してください"));
    }

    @Test
    void releaseYearに4桁未満の整数が入力された場合バリデーションエラーとなること() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        InsertForm form = new InsertForm(
                "aaaaa",
                "bbbb",
                "cccccc",
                "203");

        Set<ConstraintViolation<InsertForm>> result = validator.validate(form, ValidationGroupAll.class);
        assertThat(result.size()).isEqualTo(1);

        assertThat(result).extracting(propertyPath -> propertyPath.getPropertyPath().toString(),
                        message -> message.getMessage())
                .containsOnly(
                        tuple("releaseYear", "整数4桁で入力してください"));
    }
}