package com.task.lecture9.form;

import javax.validation.GroupSequence;

@GroupSequence({
        ValidationGroupFirst.class,
        ValidationGroupSecond.class
})

public interface ValidationGroupAll {
}
