package br.com.soccer.repositorie.helper.exception

import java.lang.Exception

class BusinessException(errorMessage: String?): Exception(errorMessage)