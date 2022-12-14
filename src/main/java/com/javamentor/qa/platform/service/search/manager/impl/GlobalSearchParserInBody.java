package com.javamentor.qa.platform.service.search.manager.impl;


import com.javamentor.qa.platform.service.search.manager.abstrats.GlobalSearchParserString;

import java.util.Optional;


public class GlobalSearchParserInBody implements GlobalSearchParserString {


    @Override
    public String parseString(String parseStr) {
        if (parseStr.startsWith("body:")) {
            return "QuestionPageDtoFromBody";
        }
        return Optional.empty().toString();
    }
}