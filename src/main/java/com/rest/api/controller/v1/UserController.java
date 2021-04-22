package com.rest.api.controller.v1;

import com.rest.api.entity.User;
import com.rest.api.model.result.CommonResult;
import com.rest.api.model.result.ListResult;
import com.rest.api.model.result.SingleResult;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;

    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping("/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다.")
    @GetMapping("/user/{userId}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable long userId) throws Exception {
        return responseService.getSingleResult(userJpaRepo.findById(userId).orElseThrow(Exception::new));
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
    @ApiImplicitParam(name = "anotherParam1", value = "Another Param1 Description", paramType = "header")
    @PostMapping("/user")
    public SingleResult<User> save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                                   @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정한다.")
    @PutMapping("/user")
    public SingleResult<User> update(
                                     @ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
                                     @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                                     @ApiParam(value = "회원이름", required = true) @RequestParam String name){
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "회원 정보를 삭제한다.")
    @DeleteMapping("/user/{msrl}")
    public CommonResult delete(@ApiParam(value = "회원번호", required = true) @PathVariable long msrl){
        userJpaRepo.deleteById(msrl);
        return responseService.getSuccessResult();
    }


}
