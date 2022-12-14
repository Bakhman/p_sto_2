package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.AnswerBodyDto;
import com.javamentor.qa.platform.models.dto.AnswerDto;
import com.javamentor.qa.platform.models.dto.AnswerUserDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.CommentAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.CommentAnswerService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.VoteAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user/question/{questionId}/answer")
@Api(value = "???????????? ?? ???????????????? ???? ??????????????", tags = {"?????????? ???? ????????????"})
public class AnswerResourceController {
    private final AnswerService answerService;
    private final CommentAnswerService commentAnswerService;
    private final AnswerDtoService answerDtoService;
    private final VoteAnswerService voteAnswerService;
    private final QuestionService questionService;

    @ApiOperation(value = "???????????????? ???????????? ???? ????????????", tags = {"???????????????? ????????????"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ????????????????"),
            @ApiResponse(code = 400, message = "???????????? ?? ?????????? ID ???? ????????????????????")})
    @DeleteMapping("/{answerId}")
    public ResponseEntity<String> deleteAnswerById(@ApiParam("Id ????????????") @PathVariable Long answerId) {
        Optional<Answer> optionalAnswer = answerService.getById(answerId);
        if (optionalAnswer.isEmpty()) {
            return ResponseEntity.badRequest().body("Answer with this ID was not found");
        }
        answerService.deleteById(answerId);
        return ResponseEntity.ok().body("Answer successfully deleted");
    }

    @ApiOperation(value = "?????????????????? ???????????? ?????????????? ???? ????????????", tags = {"?????????????????? ???????????? ??????????????"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ??????????????????")})
    @GetMapping()
    public ResponseEntity<List<AnswerDto>> getAnswerByQuestionId(@PathVariable Long questionId) {
        return ResponseEntity.ok().body(answerDtoService.getAnswerByQuestionId(questionId));
    }

    @ApiOperation(value = "?????????????????? ???????????? ?????????????? ???? ????????????", tags = {"?????????????????? ???????????? ??????????????"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ??????????????????")})
    @GetMapping("/lastWeek")
    public ResponseEntity<List<AnswerUserDto>> getAnswerForLastWeek() {
        return ResponseEntity.ok().body(answerDtoService.getAnswerForLastWeek());
    }

    @ApiOperation(value = "?????????????????????? ???? ??????????", tags = {"?????????????????? ???????????? ???????????????????? ??????????????"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ??????????????????????"),
            @ApiResponse(code = 400, message = "?????????? ?? ?????????? ID ???? ???????????? ?????? ???? ?????? ???????????????????? ???? ???????? ??????????")})
    @PostMapping("/{id}/upVote")
    public ResponseEntity<?> setUpVoteAnswerByAnswerId(@PathVariable("id") Long answerId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Optional<Answer> optionalAnswer = answerService.getById(answerId);

        /*
         * ???????????????? ?????????????? ???????????? ???? ?????????????? ???? ?????????????????????????????????? ?????????? ?? ???????????????????????? ?? ???? ????????????????
         */
        Optional<VoteAnswer> voteAnswerOptional = voteAnswerService.getByUserIdAndAnswerId(user.getId(), answerId);
        if(voteAnswerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("You allready voted for the answer with id " + answerId);
        }
        if (optionalAnswer.isEmpty()) {
            return ResponseEntity.badRequest().body("Answer with id " + answerId + " not found");
        }
        Answer answer = optionalAnswer.get();
        if (Objects.equals(answer.getUser().getId(), user.getId())) {
            return ResponseEntity.badRequest().body("Voting for your answer with id " + answerId + " not allowed");
        }
        return ResponseEntity.ok().body(voteAnswerService.postVoteUp(user, answer));
    }

    @ApiOperation(value = "?????????????????????? ???????????? ????????????", tags = {"?????????????????? ???????????? ???????????????????? ??????????????"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ??????????????????????"),
            @ApiResponse(code = 400, message = "?????????? ?? ?????????? ID ???? ???????????? ?????? ???? ?????? ???????????????????? ???? ???????? ??????????")})
    @PostMapping("/{id}/downVote")
    public ResponseEntity<?> setDownVoteAnswerByAnswerId(@PathVariable("id") Long answerId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        Optional<Answer> optionalAnswer = answerService.getById(answerId);

        /*
         * ???????????????? ?????????????? ???????????? ???? ?????????????? ???? ?????????????????????????????????? ?????????? ?? ???????????????????????? ?? ???? ????????????????
         */
        Optional<VoteAnswer> voteAnswerOptional = voteAnswerService.getByUserIdAndAnswerId(user.getId(), answerId);
        if(voteAnswerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("You allready voted for the answer with id " + answerId);
        }

        if (optionalAnswer.isEmpty()) {
            return ResponseEntity.badRequest().body("Answer with id " + answerId + " not found");
        }
        Answer answer = optionalAnswer.get();
        if (Objects.equals(answer.getUser().getId(), user.getId())) {
            return ResponseEntity.badRequest().body("Voting for your answer with id " + answerId + " not allowed");
        }
        return ResponseEntity.ok().body(voteAnswerService.postVoteDown(user, answer));
    }

    @ApiOperation(value = "???????????????????? ???????????? ???? ????????????", tags = {"???????????????????? ????????????"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ???????????????????? ????????????"),
            @ApiResponse(code = 400, message = "???????????? ???????????????????? ????????????")})
    @PostMapping("/add")
    //?????????????????? ?????????????????? ???????????? ???????? ??????????
    public ResponseEntity<?> addNewAnswer(@PathVariable Long questionId,
                                          @Valid @RequestBody AnswerBodyDto answerBodyDto) {
        Optional<Question> optionalQuestion = questionService.getById(questionId);
        if (optionalQuestion.isEmpty()){
            return ResponseEntity.badRequest().body("?????????????? ??  id " + questionId + " ???? ????????????????????");
        }
        Question question = optionalQuestion.get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (answerService.checkAnswerByQuestionIdAndUserId(questionId, user.getId())){
            return ResponseEntity.badRequest().body("?????????? ?????? ?????? ????????????????");
        }Answer answer = new Answer(question, user, answerBodyDto.getHtmlBody());
        answerService.persist(answer);
        return answerDtoService.getAnswerDtoByAnswerId(answer.getId()).isPresent() ?
                ResponseEntity.ok().body(answerDtoService.getAnswerDtoByAnswerId(answer.getId())) :
                ResponseEntity.badRequest().body("???????????? ???????????????? Dto");
    }

    @ApiOperation(value = "???????????????????? ?????????????????????? ?? ????????????", tags = {"???????????????????? ??????????????????????"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "???????????????? ???????????????????? ?????????????????????? ?? ????????????"),
            @ApiResponse(code = 400, message = "???????????? ???????????????????? ?????????????????????? ?? ????????????")})
    @PostMapping("/{id}/comment")
    public ResponseEntity<?> addNewCommentForAnswer(@PathVariable("id") Long answerId,
                                                    @Valid @RequestBody String answerComment) {
        Optional<Answer> optionalAnswer = answerService.getById(answerId);
        if (optionalAnswer.isEmpty()) {
            return ResponseEntity.badRequest().body("Answer with id " + answerId + " not found");
        }
        if (answerComment.isEmpty()) {
            return ResponseEntity.badRequest().body("Can't add an empty comment");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        CommentAnswer commentAnswer = new CommentAnswer(answerComment, user, optionalAnswer.get());
        commentAnswerService.persist(commentAnswer);

        return ResponseEntity.ok().body("Answer successfully add");
    }
}