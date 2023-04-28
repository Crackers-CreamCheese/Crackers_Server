package com.creamcheese.crackers.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExcetpion extends RuntimeException{

    ErrorCode errorCode;
}
