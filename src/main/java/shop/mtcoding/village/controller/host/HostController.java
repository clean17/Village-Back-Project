package shop.mtcoding.village.controller.host;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.core.auth.MyUserDetails;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.model.host.Host;
import shop.mtcoding.village.service.HostService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
public class HostController {

    private final HostService hostService;

    @PostMapping("user/host")
    public @ResponseBody ResponseEntity<ResponseDTO<Host>> saveHost(
            @Valid @RequestBody HostSaveRequest hostSaveDto, Errors Errors) {

        Host hostSave = hostService.호스트신청(hostSaveDto);

        return new ResponseEntity<>(new ResponseDTO<>(1, 200, "호스트 신청 완료", hostSave), HttpStatus.OK);
    }

}
