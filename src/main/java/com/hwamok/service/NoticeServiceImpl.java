package com.hwamok.service;

import com.hwamok.controller.dto.NoticeCreateDTO;
import com.hwamok.entity.Notice;
import com.hwamok.entity.User;
import com.hwamok.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeRepository noticeRepository;

    private PlatformTransactionManager platformTransactionManager;

    public NoticeServiceImpl(NoticeRepository noticeRepository, PlatformTransactionManager platformTransactionManager) {
        this.noticeRepository = noticeRepository;
        this.platformTransactionManager=platformTransactionManager;
    }

    @Override
    public void createNotice(NoticeCreateDTO dto, User user) {
        noticeRepository.save(new Notice(dto.getTitle(), dto.getContent(), user.getId()));

    }

    @Override
    public void noticeDelete(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("notice not found"));
        noticeRepository.delete(notice);
    }

    @Override
    public List<Notice> findAll() {
        System.out.println("findAll 시작");
        List<Notice> noticeList = noticeRepository.findAll();
        // 무조건 findAll은 Entity 전체가 오는 것이기 때문에 List형태의 Entity를 담아서 가지고 온다.
        //List<NoticeCreateDTO> noticeCreateDTOList = new ArrayList<>();
        System.out.println("findAll 종료");
        // List<Notice> 객체를 List<NoticeCreateDTO> 옮겨 담아서 보내줘야 한다.

        return noticeList;
    }

    @Override
    public Notice noticeView(Long id) {
       return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("notice not found"));

    }

    @Override
    public Notice noticeEdit(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("notice not found"));
    }

    @Override
    public void noticeUpdate(Long id, String title, String content) {
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionAttribute());

        Notice notice = noticeRepository.findById(id).orElseThrow(() -> new RuntimeException("notice not found"));

        try {
            if(title.isBlank()){
                throw new RuntimeException("invalidate title");
            }
            if(content.isBlank()){
                throw new RuntimeException("invalidate content");
            }
            notice.changeTitle(title);
            notice.changeContent(content);

            platformTransactionManager.commit(status);
        }catch (RuntimeException e){
            e.printStackTrace();
            platformTransactionManager.rollback(status);
        }
    }
}
