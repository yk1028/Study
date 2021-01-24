package com.yk1028.api.controller.v1

import com.yk1028.api.advice.exception.CUserNotFoundException
import com.yk1028.api.entity.User
import com.yk1028.api.model.reponse.CommonResult
import com.yk1028.api.model.reponse.ListResult
import com.yk1028.api.model.reponse.SingleResult
import com.yk1028.api.repo.UserJpaRepository
import com.yk1028.api.service.ResponseService
import io.swagger.annotations.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*


@Api(tags = ["1. User"])
@RestController
@RequestMapping(value = ["/v1"])
class UserController(private val userJpaRepo: UserJpaRepository, private val responseService: ResponseService) {

    @ApiImplicitParams(ApiImplicitParam(
            name = "X-AUTH-TOKEN",
            value = "로그인 성공 후 access_token",
            required = true,
            dataType = "String",
            paramType = "header"))
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = ["/users"])
    fun findAllUser(): ListResult<User> {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userJpaRepo.findAll())
    }

    @ApiImplicitParams(ApiImplicitParam(
            name = "X-AUTH-TOKEN",
            value = "로그인 성공 후 access_token",
            required = false,
            dataType = "String",
            paramType = "header"))
    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = ["/user/{msrl}"])
    fun findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable msrl: Long,
                     @ApiParam(value = "언어", defaultValue = "ko") @RequestParam lang: String): SingleResult<User> {
        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByIdOrNull(msrl) ?: throw CUserNotFoundException())
    }

    @ApiImplicitParams(ApiImplicitParam(
            name = "X-AUTH-TOKEN",
            value = "로그인 성공 후 access_token",
            required = true,
            dataType = "String",
            paramType = "header"))
    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = ["/user"])
    fun modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam msrl: Long,
            @ApiParam(value = "회원아이디", required = true) @RequestParam uid: String,
            @ApiParam(value = "회원이름", required = true) @RequestParam name: String): SingleResult<User> {
        val user = userJpaRepo.findByIdOrNull(msrl) ?: throw CUserNotFoundException()
        user.updateUser(uid, name)

        return responseService.getSingleResult(userJpaRepo.save(user))
    }

    @ApiImplicitParams(ApiImplicitParam(
            name = "X-AUTH-TOKEN",
            value = "로그인 성공 후 access_token",
            required = true,
            dataType = "String",
            paramType = "header"))
    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = ["/user/{msrl}"])
    fun delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable msrl: Long): CommonResult {
        userJpaRepo.deleteById(msrl)
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.successResult()
    }
}