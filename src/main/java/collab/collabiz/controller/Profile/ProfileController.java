package collab.collabiz.controller.Profile;

import collab.collabiz.accountInfra.errors.UserException;
import collab.collabiz.controller.account.AccountResource;
import collab.collabiz.entity.account.dtos.ProfileDto;
import collab.collabiz.entity.Member;
import collab.collabiz.entity.profile.UploadFile;
import collab.collabiz.service.profile.FileStore;
import collab.collabiz.repository.account.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Slf4j
@Controller
@AllArgsConstructor
public class ProfileController {
    private AccountRepository accountRepository;
    private final FileStore fileStore;

    @PostMapping("/profile")
    public ResponseEntity saveProfile(
            @RequestPart(value = "profileDto", required = false) ProfileDto profileDto,
            @RequestPart(value = "ProfileImage") MultipartFile profileImage,
            @RequestPart(value = "BannerImage") MultipartFile bannerImage,
            @RequestPart(value = "File1") MultipartFile file1,
            @RequestPart(value = "File2") MultipartFile file2,
            @RequestPart(value = "File3") MultipartFile file3,
            BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new UserException("입력값이 잘못 되었습니다.");
        }

        UploadFile attachProfileImageFile = fileStore.storeFile(profileImage);
        UploadFile attachBannerImageFile = fileStore.storeFile(bannerImage);
        UploadFile attachFile1 = fileStore.storeFile(file1);
        UploadFile attachFile2 = fileStore.storeFile(file2);
        UploadFile attachFile3 = fileStore.storeFile(file3);

        //저장된 사용자 찾음
        Member member = accountRepository.findByEmail(profileDto.getEmail());

        //프로필 이미지
        member.setUploadProfileImage(attachProfileImageFile.getUploadFileName());
        member.setStoreProfileImage(attachProfileImageFile.getStoreFileName());
        //배너 이미지
        member.setUploadBannerImage(attachBannerImageFile.getUploadFileName());
        member.setStoreBannerImage(attachBannerImageFile.getStoreFileName());
        //첨부파일1
        member.setUploadFileName1(attachFile1.getUploadFileName());
        member.setStoreFileName1(attachFile1.getStoreFileName());
        //첨부파일2
        member.setUploadFileName2(attachFile2.getUploadFileName());
        member.setStoreFileName2(attachFile2.getStoreFileName());
        //첨부파일3
        member.setUploadFileName3(attachFile3.getUploadFileName());
        member.setStoreFileName3(attachFile3.getStoreFileName());

        member.setCompanyUrl(profileDto.getCompanyUrl());
        member.setCompanyIntroduction(profileDto.getCompanyIntroduction());
        member.setCompanyContactNumber(profileDto.getCompanyContactNumber());

        //사용자 정보 수정
        accountRepository.save(member);
        EntityModel<Member> accountResource = AccountResource.modelOf(member);

        return ResponseEntity.ok(accountResource);
    }
}
