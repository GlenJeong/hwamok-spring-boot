package com.hwamok.service;

import com.hwamok.controller.dto.NoticeCreateDTO;
import com.hwamok.entity.Notice;
import com.hwamok.entity.User;
import com.hwamok.repository.NoticeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeRepository noticeRepository;

    private PlatformTransactionManager platformTransactionManager;

    public NoticeServiceImpl(NoticeRepository noticeRepository, PlatformTransactionManager platformTransactionManager) {
        this.noticeRepository = noticeRepository;
        this.platformTransactionManager=platformTransactionManager;
    }

    @Override
    public void createNotice(NoticeCreateDTO dto, User user, MultipartFile file) throws IOException {

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        // 경로 지정하기, System.getProperty("user.dir") 경로를 담아주기 +  "\\src\\main\\resources\\static\\files"; 경로 지정하기

        UUID uuid = UUID.randomUUID();// 파일에 붙일 랜덤한 식별자 만들기



        String fileName = uuid + "_" + file.getOriginalFilename();
            // DB에 저장되는 파일 이름, 파일에 붙일 랜덤한 식별자와 문자 _ 와 실제 파일 이름을 하나의 문자열로 만든다.

        String original = file.getOriginalFilename();
        System.out.println("11111111111111111111111111111111111111111111111111111111111111111111");
        System.out.println("original = " + original);
        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
        System.out.println("11111111111111111111111111111111111111111111111111111111111111111111");




        if(file.getOriginalFilename().equals("")){
            dto.uploadFileName("");
            dto.uploadFilePath("");
            dto.uploadOriginal("");
        } else {
            File saveFile = new File(projectPath, fileName);
            // 파일을 저장히기 위해서 File 클래스 추가,  new File(projectPath, "name"); 파일을 생성할 때
            // projectPath에 넣어줄것이고 fileName이라는 이름으로 담긴다.

            file.transferTo(saveFile);
            // 파일 저정하기
            dto.uploadFileName(fileName);
            dto.uploadFilePath("/files/"+fileName);
            dto.uploadOriginal(original);
        }

        if(dto.getTitle().isBlank()){
            throw new RuntimeException("invalidate title");
        }
        if(dto.getContent().isBlank()){
            throw new RuntimeException("invalidate content");
        }
        noticeRepository.save(new Notice(dto.getTitle(), dto.getContent(), user.getName(), user.getId(), dto.getOriginal(), dto.getFileName(), dto.getFilePath()));

    }

    @Override
    public Page<Notice> getNotices(String keyword, int curPage, int pageSize) {
        // 페이징이라는 것은 구조가 정해져있음
        // 필수 요소 3가지가 있음
        // 현재가 내가 보고 있는 페이지(Current page), 쿼리로 조회, select * from notice limit 2 offset 2
        // 한 페이지에 몇 개를 보여줄건지(ItemPerPage), 쿼리로 조회
        // 노티스의 총 갯수(Total Count)
        
        // 페이징 쿼리가 어떻게 동작하나?
        // DB들마다 구현 방법이 조금씩 틀림(Mysql, MariaDB, Postgresql 동일), Mssql(윈도우 게임회사), Oracle(공공기간) 조금씩 다름)
        
        // Limit = 제한 - size: 2
        // offset = 간격, 거리(Distance) - page: 0

        PageRequest pageRequest = PageRequest.of(curPage - 1, pageSize);


            return noticeRepository.getNotices(keyword, pageRequest);



        // 페이징 처리가 된 객체는 List가 아니라 Page로 변환이 됨

        // 키워드가 있을 때 검색하면 title과 content에서 포함되는 모든 게시물을 검색해와야함
        // 키워드가 없을 때는 전부 검색하면 됨


    }

    @Override
    public void noticeDelete(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("not found notice"));
        noticeRepository.delete(notice);
        // optional를 벗어나기 위해서 orElseThrow 사용해야 한다.
    }

    @Override
    public Page<Notice> findAll(int curPage, int pageSize) {
        // 필수 요소 3가지가 있음
        // 현재가 내가 보고 있는 페이지(Current page), 쿼리로 조회, select * from notice limit 2 offset 2
        // 한 페이지에 몇 개를 보여줄건지(ItemPerPage), 쿼리로 조회
        // 노티스의 총 갯수(Total Count)

        // 페이징 쿼리가 어떻게 동작하나?
        // DB들마다 구현 방법이 조금씩 틀림(Mysql, MariaDB, Postgresql 동일), Mssql(윈도우 게임회사), Oracle(공공기간) 조금씩 다름)

        // Limit = 제한 - size: 2
        // offset = 간격, 거리(Distance) - page: 0

        PageRequest pageRequest = PageRequest.of(curPage - 1, pageSize);
        Page<Notice> noticeList = noticeRepository.findAllByOrderByIdDesc(pageRequest);
        // 무조건 findAll은 Entity 전체가 오는 것이기 때문에 List형태를 Page로 바뀜 Entity를 담아서 가지고 온다.


        return noticeList;
    }

    @Override
    public Notice noticeView(Long id) {
       return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("not found notice"));
    }

    @Override
    public Notice noticeEdit(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("not found notice"));
    }

    @Override
    public void noticeUpdate(Long id, String title, String content, MultipartFile fileUpdate, String name) throws IOException{
        System.out.println("NoticeServiceImpl title = " + title);
        System.out.println("NoticeServiceImpl content = " + content);
        System.out.println("file :::::::::::::::::: = " + fileUpdate.getOriginalFilename());

        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("not found notice"));

        String projectNewPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\newFiles";
        // 경로 지정하기, System.getProperty("user.dir") 경로를 담아주기 +  "\\src\\main\\resources\\static\\files"; 경로 지정하기

        UUID uuid = UUID.randomUUID();
        // 파일에 붙일 랜덤한 식별자 만들기

        String fileName = uuid + "_" + fileUpdate.getOriginalFilename();
        // DB에 저장되는 파일 이름, 파일에 붙일 랜덤한 식별자와 문자 _ 와 실제 파일 이름을 하나의 문자열로 만든다.

        String origianl = fileUpdate.getOriginalFilename();

        try {
            if(title.isBlank()){
                throw new RuntimeException("invalidate title");
            }
            if(content.isBlank()){
                throw new RuntimeException("invalidate content");
            }

            if(name.isBlank()){
                throw new RuntimeException("invalidate name");
            }
            notice.changeTitle(title);
            notice.changeContent(content);
            notice.changeName(name);
            System.out.println("NoticeServiceImpl notice.getOriginal() = " + notice.getOriginal());
            System.out.println("NoticeServiceImpl notice.getFileName() = " + notice.getFileName());
            System.out.println("NoticeServiceImpl notice.getFilePath() = " + notice.getFilePath());

            if(fileUpdate.getOriginalFilename().equals("")){
                notice.uploadOriginal(notice.getOriginal());
                notice.uploadFileName(notice.getFileName());
                notice.uploadFilePath(notice.getFilePath());
            } else {
                File saveNewFile = new File(projectNewPath, fileName);
                // 파일을 저장히기 위해서 File 클래스 추가,  new File(projectPath, "name"); 파일을 생성할 때
                // projectPath에 넣어줄것이고 fileName이라는 이름으로 담긴다.

                fileUpdate.transferTo(saveNewFile);
                // 파일 저정하기
                notice.uploadOriginal(origianl);
                notice.uploadFileName(fileName);
                notice.uploadFilePath("/newFiles/"+fileName);
            }



            platformTransactionManager.commit(status);
        }catch (RuntimeException e){
            e.printStackTrace();
            platformTransactionManager.rollback(status);
        }
    }
}
