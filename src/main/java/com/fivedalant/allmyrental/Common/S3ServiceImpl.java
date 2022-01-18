package com.fivedalant.allmyrental.Common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class S3ServiceImpl implements S3Service{

    private final AmazonS3Client amazonS3Client;

    //S3 파일 업로드
    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest("allmyrental", fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    //파일 URL 가져오기
    @Override
    public String getFileUrl(String fileName) {
        return String.valueOf(amazonS3Client.getUrl("allmyrental", fileName));
    }

    //썸네일 업로드
    @Override
    public void uploadThumbnail(File thumbnailFile, String filename){
        amazonS3Client.putObject(new PutObjectRequest("allmyrental", filename, thumbnailFile).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    //S3 파일 삭제
    @Override
    public void deleteFile(String deletePath){
        amazonS3Client.deleteObject(new DeleteObjectRequest("allmyrental",deletePath));
    }
}