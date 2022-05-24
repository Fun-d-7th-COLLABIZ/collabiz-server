package collab.collabiz.controller.Profile;

import collab.collabiz.accountInfra.errors.UserException;
import collab.collabiz.controller.account.AccountResource;
import collab.collabiz.entity.account.dtos.ProfileDto;
import collab.collabiz.entity.Member;
import collab.collabiz.entity.profile.UploadFile;
import collab.collabiz.repository.MemberRepository;
import collab.collabiz.service.profile.FileStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
public class ProfileController {
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    @ResponseBody
    @GetMapping("/profile/{memberId}")
    public ResponseEntity<ResponseProfileDto> profile(@PathVariable Long memberId) throws MalformedURLException {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();
        String storeProfileImageName = member.getStoreProfileImage();
        String uploadProfileImageName = member.getUploadProfileImage();
        String storeBannerImageName = member.getStoreBannerImage();
        String uploadBannerImageName = member.getUploadBannerImage();

        String encodeUploadProfileImageName = UriUtils.encode(uploadProfileImageName, StandardCharsets.UTF_8);
        String encodeUploadBannerImageName = UriUtils.encode(uploadBannerImageName, StandardCharsets.UTF_8);
        Resource resourceProfileImage = new UrlResource("file:"+ fileStore.getFullPath(storeProfileImageName));
        Resource resourceBannerImage = new UrlResource("file:"+ fileStore.getFullPath(storeBannerImageName));

        ResponseProfileDto responseProfileDto = new ResponseProfileDto(resourceProfileImage,resourceBannerImage,
                member.getCompanyName(),member.getBusinessRegistrationNumber(),member.getCompanyIntroductionSummary(),member.getCompanyIntroduction(),
                member.getUploadFileName1(),member.getUploadFileName2(),member.getUploadFileName3());
        return new ResponseEntity(responseProfileDto, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/profileTest/{memberId}")
    public Resource profileTest(@PathVariable Long memberId) throws MalformedURLException {
       Optional<Member> memberFind = memberRepository.findById(memberId);
        Member member = memberFind.get();

        return new UrlResource("file:" + fileStore.getFullPath(member.getStoreProfileImage()));
    }

    @GetMapping("/profile/file1/{memberId}")
    public ResponseEntity<Resource> downloadFile1(@PathVariable Long memberId)throws MalformedURLException{
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();
        String storeFileName = member.getStoreFileName1();
        String uploadFileName = member.getUploadFileName1();

        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        UrlResource resource = new UrlResource("file:"+ fileStore.getFullPath(storeFileName));

        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(resource);
    }

    @GetMapping("/profile/file2/{memberId}")
    public ResponseEntity<Resource> downloadFile2(@PathVariable Long memberId)throws MalformedURLException{
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();
        String storeFileName = member.getStoreFileName2();
        String uploadFileName = member.getUploadFileName2();
        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        UrlResource resource = new UrlResource("file:"+ fileStore.getFullPath(storeFileName));

        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(resource);
    }

    @GetMapping("/profile/file3/{memberId}")
    public ResponseEntity<Resource> downloadFile3(@PathVariable Long memberId)throws MalformedURLException{
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();
        String storeFileName = member.getStoreFileName3();
        String uploadFileName = member.getUploadFileName3();
        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        UrlResource resource = new UrlResource("file:"+ fileStore.getFullPath(storeFileName));

        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(resource);
    }

    //서비스 로직 분리 필요.
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
        //저장된 사용자 찾음
        Optional<Member> findMember = memberRepository.findByEmail(profileDto.getEmail());

        Member member = findMember.get();

        UploadFile attachProfileImageFile = fileStore.storeFile(profileImage);
        UploadFile attachBannerImageFile = fileStore.storeFile(bannerImage);

        //List로 바꾸기.
        //첨부파일은 필수사항 아님.
        if(!file1.isEmpty()) {
            UploadFile attachFile1 = fileStore.storeFile(file1);
            member.setUploadFileName1(attachFile1.getUploadFileName());
            member.setStoreFileName1(attachFile1.getStoreFileName());
        }
        if(!file2.isEmpty()) {
            UploadFile attachFile2 = fileStore.storeFile(file2);
            member.setUploadFileName2(attachFile2.getUploadFileName());
            member.setStoreFileName2(attachFile2.getStoreFileName());
        }
        if(!file3.isEmpty()) {
            UploadFile attachFile3 = fileStore.storeFile(file3);
            member.setUploadFileName3(attachFile3.getUploadFileName());
            member.setStoreFileName3(attachFile3.getStoreFileName());
        }

        //프로필 이미지
        member.setUploadProfileImage(attachProfileImageFile.getUploadFileName());
        member.setStoreProfileImage(attachProfileImageFile.getStoreFileName());
        //배너 이미지
        member.setUploadBannerImage(attachBannerImageFile.getUploadFileName());
        member.setStoreBannerImage(attachBannerImageFile.getStoreFileName());

        member.setCompanyUrl(profileDto.getCompanyUrl());
        member.setCompanyIntroductionSummary(profileDto.getCompanyIntroductionSummary());
        member.setCompanyIntroduction(profileDto.getCompanyIntroduction());
        member.setCompanyContactNumber(profileDto.getCompanyContactNumber());

        memberRepository.save(member);
        EntityModel<Member> accountResource = AccountResource.modelOf(member);


        return ResponseEntity.ok(accountResource);
    }
}
