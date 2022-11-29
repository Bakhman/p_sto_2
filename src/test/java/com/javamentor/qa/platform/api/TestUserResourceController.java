package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestUserResourceController extends AbstractApiTest {


    //Top 10 проверка на кол-во < 10, порядок, граница неделя
    @Test
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetTop10ByAnswerPerWeek() throws Exception {

        mvc.perform(get("/api/user/top/answer/week")
                        .header("Authorization", getJwtToken("vasya@mail.ru", "password")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(102))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalAnswers").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(104))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalAnswers").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value(105))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].totalAnswers").value(4))
                /* проверка userDto*/
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].totalAnswers").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].email").value("vasya@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].reputation").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reputation").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].reputation").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].fullName").value("Vas"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].linkImage").value("a.b"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].city").value("city"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].dateRegister").value("2010-10-10T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].totalVotesOnAnswers").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalVotesOnAnswers").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalVotesOnAnswers").value(1));
    }

    //test null Answer
    @Test
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/BeforeNullAnswer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

    public void testGetTop10ByAnswerPerWeekNull() throws Exception {

        mvc.perform(get("/api/user/top/answer/week")
                        .header("Authorization", getJwtToken("vasya@mail.ru", "password")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
    }

    //проверка на кол-во 10=10, граница месяц
    @Test
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetTop10ByAnswerPerMonth() throws Exception {

        mvc.perform(get("/api/user/top/answer/month")
                        .header("Authorization", getJwtToken("vasya@mail.ru", "password")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(102))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalAnswers").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(104))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalAnswers").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].totalAnswers").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].email").value("vasya@mail.ru"));
    }

    //проверка  на кол-во >10, граница год
    @Test
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetTop10ByAnswerPerYear() throws Exception {
        mvc.perform(get("/api/user/top/answer/year")
                        .header("Authorization", getJwtToken("vasya@mail.ru", "password")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalAnswers").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("vasya@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(102))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].totalAnswers").value(9));
    }

    //проверка работы с пустой табл answer
    @Test
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/BeforeNullAnswer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetCountOfAnswersByUserToWeekNull() throws Exception {
        ResultActions res = mvc.perform(get("/api/user/profile/question/week")
                .header("Authorization", getJwtToken("vasya@mail.ru", "password")));
        res.andExpect(status().isOk());
        assertThat(Integer.valueOf(res.andReturn().getResponse().getContentAsString())).isEqualTo(0);
    }

    //проверка кол-во ответов
    @Test
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/userResourceController/getTop10ByAnswerAndCountAnswerByUser/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetCountOfAnswersByUserToWeek() throws Exception {
        ResultActions res101 = mvc.perform(get("/api/user/profile/question/week")
                .header("Authorization", getJwtToken("vasya@mail.ru", "password")));
        res101.andExpect(status().isOk());
        assertThat(Integer.valueOf(res101.andReturn().getResponse().getContentAsString())).isEqualTo(4);
        ResultActions res102 = mvc.perform(get("/api/user/profile/question/week")
                .header("Authorization", getJwtToken("user2@mail.ru", "3111")));
        res102.andExpect(status().isOk());
        assertThat(Integer.valueOf(res102.andReturn().getResponse().getContentAsString())).isEqualTo(8);
    }
}


