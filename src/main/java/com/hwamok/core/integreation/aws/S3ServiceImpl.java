package com.hwamok.core.integreation.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3ServiceImpl implements S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public S3ServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public List<Pair<String, String>> upload(List<MultipartFile> multipartFiles) {
        System.out.println("multipartFiles = " + multipartFiles);

        List<Pair<String, String>> pairs = new ArrayList<>();

        multipartFiles.forEach(file -> {
            String savedFileName = createFileName(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            System.out.println("S3ServiceImpl file.getOriginalFilename() = " + file.getOriginalFilename());

            ObjectMetadata objectMetadata = new ObjectMetadata();

            String fileSavedPath = amazonS3.getUrl(bucket, savedFileName).toString();

            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(bucket, savedFileName, inputStream, objectMetadata);
            }catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 업로드에 실패 하였습니다.");
            }

            System.out.println("S3ServiceImpl fileName = " + fileName);
            System.out.println("S3ServiceImpl savedFileName = " + savedFileName);
            System.out.println("S3ServiceImpl fileSavedPath = " + fileSavedPath);


            pairs.add(Pair.of(fileName, savedFileName));
        });

        return pairs;
    }

    @Override
    public boolean delete(String savedFileName) {
        if(amazonS3.doesObjectExist(bucket, savedFileName)) {
            amazonS3.deleteObject(new DeleteObjectRequest(bucket, savedFileName));

            return true;
        }

        return false;
    }

    private String createFileName(String name) {
        return "F_" + System.currentTimeMillis() + getExtension(name);
    }

    private String getExtension(String name) {
        try {
            return name.substring(name.lastIndexOf("."));
        }catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘 못 된 형식의 파일입니다.");
        }
    }
}
